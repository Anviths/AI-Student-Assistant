package com.anv.agent;

import com.anv.tool.StudentTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StudentAgent implements Agent {

    private final ChatClient chatClient;

    public StudentAgent(ChatClient.Builder builder,
                        ChatMemory chatMemory,
                        StudentTool studentTool) {

        this.chatClient = builder
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(chatMemory).build()
                )

                .defaultSystem("""
                        You are an expert Student Management Agent.

                        Your responsibilities are:
                        - Student Profile
                        - Student Details
                        - Student Information
                        - Course Details
                        - Branch Details
                        - Batch Information
                        - Trainer Information
                        - Student Status

                        Rules:
                        1. Always use StudentTool whenever live student information is required.
                        2. If the answer exists in the knowledge base, use RAG.
                        3. If both live data and documents are required, combine both.
                        4. Never guess student information.
                        5. Respond politely and professionally.
                        """)

                .defaultTools(studentTool)

                .build();
    }

    @Override
    public boolean canHandle(String prompt) {

        if (prompt == null || prompt.isBlank()) {
            return false;
        }

        String text = prompt.toLowerCase();

        return text.contains("student")
                || text.contains("profile")
                || text.contains("batch")
                || text.contains("course")
                || text.contains("branch")
                || text.contains("trainer")
                || text.contains("admission")
                || text.contains("enrollment")
                || text.contains("registration");
    }

    @Override
    public String execute(String prompt) {

        log.info("StudentAgent handling request : {}", prompt);
        long start = System.currentTimeMillis();
        String response= chatClient.prompt()
                .user(prompt)
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, "test-session"))
                .call()
                .content();

        log.info("Time: {} ms", System.currentTimeMillis() - start);
        return response;
    }

}