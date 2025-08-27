//used for ServerSocket and Socket
import java.net.*;
//used to store clients
import java.util.concurrent.ConcurrentHashMap;
//I/O operations used for BufferedReader and PrintWriter
import java.io.*;
//It defines the Server class
public class Server{
        // server socket object to connect with client
        ServerSocket server;
        //stroes the username
        ConcurrentHashMap<String, PrintWriter> clients= new ConcurrentHashMap<>();
        //constructor
        public Server(){
        try {
        //create server socket on port 6060            
        server=new ServerSocket(6060);
        System.out.println("Admin is ready to accept the connections");
        System.out.println("Waiting...");
         //thread to handle server console input
           new Thread(() ->{
            try {
                        
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                //read input from admin
              String msg = consoleReader.readLine();
              if (msg.equalsIgnoreCase("bye")) {
                     //inform all clients
                     broadcast("bye");
                     //close client connections
                     closeAllClients();
                     //stop the server
                     server.close();            
                    System.out.println(" Server is terminated the chat.");
                    //exit the admin thread
                    break;
                     }
                     //msg from admin to all clients
                     broadcast("Admin: " +msg);
                }
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }).start();
                    //accepting the clients
                    while (true) {
                        //wait for client to connect
                        Socket socket= server.accept();
                        System.out.println("Admin: " + "Client is connected " +socket);
        
                        //Start a new thread for client
                        new Thread(new ClientHandler(socket)).start();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
               
        
            //Broadcat msg to the all clients
            public void broadcast(String msg){
        
                for(PrintWriter writer : clients.values()){
                    
                    writer.println(msg);
                    writer.flush();
                }
                //print on server console
                System.out.println(msg);
            }
            //close all client connections
            void closeAllClients(){
                for(PrintWriter w : clients.values()){
                     try{
                        //tell client to disconnet
                        w.println("bye");
                        w.flush();
                        w.close();
                     }catch(Exception e){
        
                     }
                }
                //remove all from list
                clients.clear();
            }
        
            //handles individual client
            public class ClientHandler implements Runnable{
                //client socket
                Socket socket;
                //to read msgs from client
                BufferedReader br;
                //to send msgs to client
                PrintWriter out;
                //clients username
               String username;
               //handling the client 
                public ClientHandler(Socket socket) {
                    this.socket=socket;
                
                }
        
                @Override
                public void run() {
                    try {
                         //reading the data from br 
                        br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        out=new PrintWriter(socket.getOutputStream(),true);
                        //First msg from the client
                        username=br.readLine();
                        if(username==null|| username.trim().isEmpty()){
                            //fall back
                            username="User" + socket.getPort();
    
                        }
                        //save client with username
                        clients.put(username, out);
                        //inform that the client is joined
                        broadcast("Admin: " +username +   "  has joined the chat ");
                       String msg;
                       //read msgs from client
                       while ((msg= br.readLine())!=null) {
                        if(msg.equalsIgnoreCase("bye")){
                            //diconnect
                            break;
                        }
                       //clients msg to all
                        broadcast(username + ": " + msg);
                        
                       }
                    } catch (Exception e) {
                        System.out.println(" Connection closed for " + username);
                        
                    }finally{
                        //when linet disconnect
                        if(username !=null){
                            //remove from list
                            clients.remove(username);
                            broadcast("Admin: " + username +  "  has left the chat ");
                        }
                        try {
                            //close the socket
                            socket.close();
                        } catch (IOException ignored) {
                            
         }
      }                                   
   }
}
        //main method
        public static void main(String[] args) {
        System.out.println("This is Server.. going to start");
        ////create new server object
         new Server();
             
        
   }
}
