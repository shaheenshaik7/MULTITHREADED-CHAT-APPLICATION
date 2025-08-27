# MULTITHREADED-CHAT-APPLICATION

*COMPANY*: CODTECH IT SOLUTIONS

*NAME*: SHAIK SHAHEEN

*INTERN ID*: CT04DY655

*DOMAIN*: JAVA PROGRAMMING

*DURATION*: 4 WEEKS

*MENTOR*: NEELA SANTOSH

##This project implements a multithreaded client-server chat application using Java sockets. The main purpose of this application is to allow multiple clients to connect to a central server and communicate with each other in real time. The server acts as an intermediary, receiving messages from one client and broadcasting them to all other connected clients. Both the client and server codes are written in Java, leveraging the language’s strong networking and multithreading support.

The server program creates a ServerSocket object bound to port 6060. It waits for incoming client connections using the accept() method. Once a client connects, the server starts a dedicated thread to handle communication with that client. The usernames and output streams of connected clients are stored in a ConcurrentHashMap so that the server can broadcast messages to all connected clients simultaneously. This design allows multiple clients to communicate with each other in a group chat. The ConcurrentHashMap ensures thread safety while multiple clients are interacting at the same time. The server essentially acts as a message distributor, receiving input from one client and sending it to all others. This forms the backbone of a multi-user chat server.

The client program establishes a connection to the server using a Socket object (on the same port 6060). It uses a BufferedReader to read incoming messages from the server and a PrintWriter to send outgoing messages. The client prompts the user to enter a username, which is then shared with the server and other connected users. Once connected, the client runs in a loop, continuously listening for messages from the server and simultaneously allowing the user to send new messages. This design provides real-time two-way communication between multiple users in the chat system.

The code is written in Java, a powerful object-oriented programming language widely used for building cross-platform applications.In this used Libraries and APIs like "java.net.*" is used for networking classes like "ServerSocket" and "Socket", "java.io.*"  is Provides input/output classes like "BufferedReader" and "PrintWriter" to read and write messages and "java.util.concurrent.ConcurrentHashMap" is used to store connected clients with a thread.

The Multithreaded-Chat-Application can be applied in various real-time applications like Chat Application the code forms the basis of group chat systems like WhatsApp Web, Discord. it shows how multiple clients can connect to a central server and exchange real-time messages,
Customer Support Systems in this the Businesses can use such a system for live chat support, where clients connect to a server and get responses from support staff,
Online Multiplayer Games used in  Real-time games often rely on client-server communication for sending player actions, chat messages, or updates. This code can be extended to handle such interactions,
Collaborative Application Tools like Google Docs or Trello require real-time synchronization. A similar socket-based communication model ensures that updates made by one user are reflected to all others instantly and Educational Tools used for Teachers  the Teachers can host online discussions where students connect as clients to the server, simulating a virtual classroom.

The Multithreaded-Chat-Application is platform independent the code is run in Visual Studio Code in this JDK and java libraries are used. The Chat Application is socket-based client server Communication. The code is runs in command line javac and java. With modifications, this foundation can be applied in various domains like chat systems, games, customer support, and collaborative applications.


