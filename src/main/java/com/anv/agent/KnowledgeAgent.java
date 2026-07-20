package com.anv.agent;


import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KnowledgeAgent implements Agent {

    private final ChatClient chatClient;

    public KnowledgeAgent(ChatClient.Builder builder,
                          ChatMemory chatMemory,
                          VectorStore vectorStore) {

        this.chatClient = builder

                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(chatMemory).build(),
                        QuestionAnswerAdvisor.builder(vectorStore)
                .searchRequest(SearchRequest.builder()
                        .topK(3)
                        .build()

                ).build())

                .defaultSystem("""
                        You are the Institute Knowledge Assistant.

                        Your job is to answer questions ONLY from the institute knowledge base.

                        Topics include:
                        • Refund Policy
                        • Attendance Policy
                        • Leave Policy
                        • Examination Rules
                        • Placement Policy
                        • Institute Rules
                        • Course Information
                        • Fee Policy
                        • Holiday Policy
                        • Code of Conduct
                        • General FAQs

                        Rules:
                        1. Always answer using the retrieved documents.
                        2. Never invent information.
                        3. If the information is not found in the knowledge base, politely say:
                           "I couldn't find this information in the institute knowledge base."
                        4. Do NOT use tools.
                        5. Keep answers concise and professional.
                        """)
                .build();
    }

    @Override
    public boolean canHandle(String prompt) {

        if (prompt == null || prompt.isBlank()) {
            return false;
        }

        String text = prompt.toLowerCase();

        return text.contains("policy")
                || text.contains("refund")
                || text.contains("attendance policy")
                || text.contains("leave")
                || text.contains("holiday")
                || text.contains("exam")
                || text.contains("examination")
                || text.contains("placement")
                || text.contains("rule")
                || text.contains("guideline")
                || text.contains("faq")
                || text.contains("procedure");
    }

    @Override
    public String execute(String prompt) {

        log.info("KnowledgeAgent handling request: {}", prompt);

        return chatClient.prompt()
                .user(prompt)
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, "test-session"))
                .call()
                .content();
    }
}
