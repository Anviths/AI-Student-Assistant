package com.anv.controller;

import com.anv.agent.AgentRouter;
import com.anv.dto.AIResponse;
import com.anv.dto.PromptRequest;
import com.anv.propmt.SystemPrompt;
import com.anv.service.StudentService;
import com.anv.tool.StudentTool;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ai")
public class StudentAIController {

    private final AgentRouter agentRouter;

    @PostMapping("/chat/{studentId}")
    public AIResponse chat(@PathVariable long studentId, @RequestBody PromptRequest request) {

        String response = agentRouter.route(request.getPrompt());

        return new AIResponse(response);
    }
    
}
