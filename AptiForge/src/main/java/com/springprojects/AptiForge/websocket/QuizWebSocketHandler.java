package com.springprojects.AptiForge.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.springprojects.AptiForge.model.Question;

import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.TextMessage;
import org.springframework.stereotype.Component;

@Component
public class QuizWebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // This method handles incoming messages from clients
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // You can process the answer or update the score here
        String participantId = session.getId(); // Getting participant ID from WebSocket session
        String answer = message.getPayload();   // Answer received from the participant

        // Here, call your method to process the answer and update scores in Redis
        processAnswer(participantId, answer);
    }

    private void processAnswer(String participantId, String answer) {
        // Simulate processing the answer and updating Redis for participant score
        // You would update Redis and then broadcast the score update

        // Example: Get score from Redis, update, and broadcast
        messagingTemplate.convertAndSend("/topic/quiz/roomCode", "Participant " + participantId + " updated score");
    }

    // This method sends quiz questions to all connected clients
    public void sendQuizQuestion(String roomCode, Question question) {
        messagingTemplate.convertAndSend("/topic/quiz/" + roomCode, question);
    }
}
