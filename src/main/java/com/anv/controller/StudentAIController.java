package com.anv.controller;

import com.anv.dto.AIResponse;
import com.anv.dto.PromptRequest;
import com.anv.propmt.SystemPrompt;
import com.anv.service.StudentService;
import com.anv.tool.StudentTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
public class StudentAIController {
    private final ChatClient chatClient;
    private final StudentService studentService;
    private static final String CHAT_ID = ChatMemory.CONVERSATION_ID;

    public StudentAIController(ChatClient.Builder chatClient, StudentService studentService, StudentTool studentTool,ChatMemory chatMemory) {
        this.chatClient = chatClient
                .defaultTools(studentTool)
                .defaultSystem(SystemPrompt.SYSTEM_PROMPT)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
        this.studentService = studentService;

    }

    @PostMapping("/chat")
    public AIResponse chat(@RequestBody PromptRequest request) {

        String response = chatClient.prompt()
                .user(request.getPrompt())
                .advisors(a->a.param(CHAT_ID,1))
                .call()
                .content();

        return new AIResponse(response);
    }
    
}
