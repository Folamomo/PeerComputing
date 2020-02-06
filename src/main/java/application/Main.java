//package application;
//
//
//
//public class Main {
//
//    public static void main(String[] args) {
//        boolean connected = NetworkStatus.connect(3);
//        if (!connected) {
//            return;
//        }
//        Thread network_job_listener = new TasksListener();
//        network_job_listener.start();
//        Thread results_listener = new ResultListener();
//        results_listener.start();
//        Thread user_interface = new UserInterfaceHandler();
//        user_interface.start();
//    }
//}