import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Queue<LogEntry> logQueue = new Queue<>();
        Stack<LogEntry> errorStack = new Stack<>();

        int infoCount = 0;
        int warnCount = 0;
        int errorCount = 0;
        int memoryWarnings = 0;

        List<LogEntry> recentErrors = new ArrayList<>();

        // Use Scanner to read from System.in (standard input)
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            LogEntry logEntry = parseLogEntry(line);
            logQueue.enqueue(logEntry);
        }
        scanner.close();

        // Process the log entries in the queue
        while (!logQueue.isEmpty()) {
            LogEntry logEntry = logQueue.dequeue();
            String logLevel = logEntry.getLogLevel();

            if (logLevel.equals("INFO")) {
                infoCount++;
            } else if (logLevel.equals("WARN")) {
                warnCount++;
                if (logEntry.getMessage().contains("Memory")) {
                    memoryWarnings++;
                }
            } else if (logLevel.equals("ERROR")) {
                errorCount++;
                errorStack.push(logEntry);
                if (recentErrors.size() >= 100) {
                    recentErrors.remove(0);  // Remove oldest error if list is full
                }
                recentErrors.add(logEntry);
            }
        }

        // Output results
        System.out.println("INFO count: " + infoCount);
        System.out.println("WARN count: " + warnCount);
        System.out.println("ERROR count: " + errorCount);
        System.out.println("Memory warnings: " + memoryWarnings);
        System.out.println("Last 100 Errors:");
        for(LogEntry logEntry : recentErrors) {
            System.out.println(logEntry);
        }
    }

    public static LogEntry parseLogEntry(String line) {
        if (line.length() < 20 || !line.contains("] ")) {
            return new LogEntry("Invalid", "Invalid", "Invalid log entry format");
        }

        String timestamp = line.substring(1, 20);
        int levelStart = line.indexOf("] ") + 2;
        int levelEnd = line.indexOf(" ", levelStart);
        String logLevel = line.substring(levelStart, levelEnd);
        String message = line.substring(levelEnd + 1);
        return new LogEntry(timestamp, logLevel, message);
    }
}
