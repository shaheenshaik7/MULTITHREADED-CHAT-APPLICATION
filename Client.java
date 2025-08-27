//used for ServerSocket and Socket
import java.net.*;
//I/O operations used for BufferedReader and PrintWriter
import java.io.*;
//It defines the Client class
public class Client {
        //socket object to connect with server      
         Socket socket;
         //to read msgs from server
         BufferedReader br;
         //to send msgs to server
         PrintWriter out;
         // stores clients username
         String username;
         //control reading/writing 
         boolean running=true;
         //constructor
        public Client(){
            try {
                
             System.out.println("Sending request to server");
             //connect to the server at (127.0.0.1) on port 6060
             socket=new Socket("127.0.0.1", 6060);
            System.out.println("Connection Activated");
                
            //reading the data from br
             br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
             //writing the data from the out
             out=new PrintWriter(socket.getOutputStream(),true);
            //ask username from console and send frist
            System.out.print("Client: ");
             BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
             //read username input and send to server
             username = console.readLine();
                 out.println(username);
                //start reading thread
                startReading();
                 //start writing thread
                startWriting();
                            
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                         //read msgs from the server
                    public void startReading(){
                     // create  thread using lambda and read  the data 
                
                      Runnable r=()->{
                          System.out.println("reader started...");
                            try {      
                           String msg; 
                          while((msg=br.readLine()) !=null){
                            // server sends bye and terminate the chat  
                             if (msg.equalsIgnoreCase("bye") || msg.contains("Server is terminated the chat")){
                                System.out.println("Server is closed the chat");
                                //stop writer
                                running=false;
                                //close client socket
                                socket.close();
                                //exit reading 
                                break;
                            }
                              //print the msg to client
                              System.out.println(msg);
                          }
                          }catch (IOException e) {
                                   System.out.println("Connection Is Closed");
                                   //stop writer if exception is occurred
                                   running=false;
                      }
                       
                    };
                    //start thread for reading
                    new Thread(r).start();
                }
                    //take input from console and send to server
                    
                    public void startWriting(){
                     //create thread for writing
                        Runnable r1=()->{
                           System.out.println("writer started...");
                                try {
                                    // to read msgs by user
                                    BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
                            
                            while(running && !socket.isClosed()){
                                //read user input
                                String content=console.readLine();
                                   //skip if empty
                                   if(content==null)
                                   continue;
                                   //send user input to server
                                    out.println(content);
                                    //close the connection
                                    if(content.equalsIgnoreCase("bye")){
                                        //stop loops
                                        running=false;
                                        //close socket
                                        socket.close();
                                        //exit
                                        break;
                                    }
                                    
                                } 
                                }catch (Exception e) {
                                  System.out.println("Connection Is Closed");
                
                            }
                            
                
        };
        //start thread for writing
         new Thread(r1).start();
}
       //main method
       public static void main(String[]args){
       System.out.println("This is Client going to start");
        //create new client object
         new Client();

    }
}
            
    
    
    
