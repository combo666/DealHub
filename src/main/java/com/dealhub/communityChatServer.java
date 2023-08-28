package com.dealhub;

import java.io.*;
import java.net.*;
import java.util.*;

public class communityChatServer {

    private static final int PORT = 8080;
    private static Map<String, PrintWriter> clientWriters = new HashMap<>();
    public static List<String> chatHistory = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started on port " + PORT);

            while (true) {
                new ClientHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                String clientName = "User-" + clientWriters.size();
                clientWriters.put(clientName, out);

                System.out.println(clientName + " connected");

                synchronized (chatHistory) {
                    for (String message : chatHistory) {
                        out.println(message);
                    }
                }

                for (PrintWriter writer : clientWriters.values()) {
                    if (writer != out) {
                        writer.println(clientName + " joined the chat.");
                    }
                }

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    if (inputLine.equalsIgnoreCase("/exit")) {
                        break;
                    }
                    String fullMessage = clientName + ": " + inputLine;
                    synchronized (chatHistory) {
                        chatHistory.add(fullMessage);
                        saveChatHistoryToFile(fullMessage);
                    }
                    broadcastMessage(fullMessage, "", out);
                }

                clientWriters.remove(clientName);
                out.close();
                in.close();
                socket.close();
                System.out.println(clientName + " disconnected");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void broadcastMessage(String message, String senderName, PrintWriter sender) {
            synchronized (clientWriters) {
                for (PrintWriter writer : clientWriters.values()) {
                    if (writer != sender) {
                        writer.println(senderName + " " + message);
                    }
                }
            }
        }

        private void saveChatHistoryToFile(String message) {
            try (PrintWriter logWriter = new PrintWriter(new FileWriter("server_log.txt", true))) {
                logWriter.println(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
