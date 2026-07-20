package com.anv.agent;

public interface Agent {
    boolean canHandle(String prompt);

    String execute(String prompt);
}
