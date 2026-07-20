package com.anv.agent;

import com.anv.tool.FeeTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FeeAgent implements Agent {

    private final ChatClient chatClient;

    public FeeAgent(ChatClient.Builder builder,
                    ChatMemory chatMemory,
                    FeeTool feeTool) {

        this.chatClient = builder
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(chatMemory).build()
                )
                .defaultSystem("""
                        You are an expert Fee Management Agent.

                        Your responsibilities are:
                        - Fee Details
                        - Pending Fee
                        - Paid Fee
                        - Payment History
                        - Fee Receipts
                        - Payment Status
                        - Transaction Details

                        Rules:
                        1. Always use FeeTool whenever live fee information is required.
                        2. If the answer exists in the knowledge base, use RAG.
                        3. If both live data and documents are required, combine both.
                        4. Never guess fee information.
                        5. Respond politely and professionally.
                        """)

                .defaultTools(feeTool)

                .build();
    }

    @Override
    public boolean canHandle(String prompt) {

        if (prompt == null || prompt.isBlank()) {
            return false;
        }

        String text = prompt.toLowerCase();

        return text.contains("fee")
                || text.contains("fees")
                || text.contains("payment")
                || text.contains("receipt")
                || text.contains("pending")
                || text.contains("paid")
                || text.contains("transaction")
                || text.contains("invoice");
    }

    @Override
    public String execute(String prompt) {

        log.info("FeeAgent handling request : {}", prompt);

        return chatClient.prompt()
                .user(prompt)
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, "test-session"))
                .call()
                .content();
    }
}