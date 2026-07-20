package com.anv.agent;

import com.anv.tool.AttendanceTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AttendanceAgent implements Agent {

    private final ChatClient chatClient;

    public AttendanceAgent(ChatClient.Builder chatClientBuilder,
                           ChatMemory chatMemory,
                           AttendanceTool attendanceTool) {


        this.chatClient = chatClientBuilder
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(chatMemory).build()
                )
                .defaultSystem("""
                        You are an expert Attendance Management Agent.

                        Your responsibilities are:
                        - Attendance Details
                        - Attendance Percentage
                        - Present Days
                        - Absent Days
                        - Leave Records
                        - Monthly Attendance
                        - Attendance History

                        Rules:
                        1. Always use AttendanceTool whenever live attendance information is required.
                        2. If the answer exists in the knowledge base, use RAG.
                        3. If both live data and documents are required, combine both.
                        4. Never guess attendance information.
                        5. Respond politely and professionally.
                        """)

                .defaultTools(attendanceTool)

                .build();

    }

    @Override
    public boolean canHandle(String prompt) {

        if (prompt == null || prompt.isBlank()) {
            return false;
        }

        String text = prompt.toLowerCase();

        return text.contains("attendance")
                || text.contains("present")
                || text.contains("absent")
                || text.contains("leave")
                || text.contains("percentage")
                || text.contains("holiday")
                || text.contains("monthly attendance")
                || text.contains("attendance history");
    }

    @Override
    public String execute(String prompt) {

        log.info("AttendanceAgent handling request : {}", prompt);

        return chatClient.prompt()
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, "test-session"))
                .user(prompt)
                .call()
                .content();
    }
}