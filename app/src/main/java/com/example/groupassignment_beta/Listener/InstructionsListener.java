package com.example.groupassignment_beta.Listener;

import com.example.groupassignment_beta.Models.InstructionsResponse;

import java.util.List;

public interface InstructionsListener {
    void didFetch(List<InstructionsResponse> response, String message);
    void didError(String message);
}
