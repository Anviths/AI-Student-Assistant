package com.anv.agent;


import com.anv.tool.BatchTool;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;

@Component
public class BatchAgent implements Agent {

    private final ChatClient chatClient;

    public BatchAgent(ChatClient.Builder builder,
                      ChatMemory chatMemory,
                      BatchTool batchTool) {

        this.chatClient = builder
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(chatMemory).build()
                )
                .defaultSystem("""
                        You are Batch Agent.

                        Responsibilities:
                        - Batch Details
                        - Trainer Information
                        - Batch Schedule
                        - Room Number
                        - Batch Timing

                        Always use BatchTool for live data.
                        Use RAG for policies or documentation.
                        """)

                .defaultTools(batchTool)

                .build();

    }

    @Override
    public boolean canHandle(String prompt) {

        String text = prompt.toLowerCase();

        return text.contains("batch")
                || text.contains("trainer")
                || text.contains("schedule")
                || text.contains("timing")
                || text.contains("room");
    }

    @Override
    public String execute(String prompt) {

        return chatClient.prompt()
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, "test-session"))
                .user(prompt)
                .call()
                .content();

    }

}