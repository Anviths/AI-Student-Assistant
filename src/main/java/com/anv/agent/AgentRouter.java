package com.anv.agent;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AgentRouter {



    private final KnowledgeAgent knowledgeAgent;
    private final StudentAgent studentAgent;
    private final AttendanceAgent attendanceAgent;
    private final FeeAgent feeAgent;
    private final BatchAgent batchAgent;
    public String route(String prompt) {


            if (knowledgeAgent.canHandle(prompt)) {
                return knowledgeAgent.execute(prompt);
            }

            if (attendanceAgent.canHandle(prompt)) {
                return attendanceAgent.execute(prompt);
            }

            if (feeAgent.canHandle(prompt)) {
                return feeAgent.execute(prompt);
            }

            if (batchAgent.canHandle(prompt)) {
                return batchAgent.execute(prompt);
            }


        return studentAgent.execute(prompt);
    }
}