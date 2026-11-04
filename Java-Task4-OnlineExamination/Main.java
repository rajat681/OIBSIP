import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {

    // ---------------- USER DETAILS ----------------

    private String username = "student";
    private String password = "1234";
    private String displayName = "Student";

    // ---------------- EXAM DATA ----------------

    private String[] questions = {
            "Which language is used for this project?",
            "Which keyword is used to create a class in Java?",
            "Which method is the starting point of a Java program?",
            "Which Swing component is used to create a button?",
            "Which package contains Swing classes?",
            "Which keyword is used to create an object?",
            "Which data type stores true or false?",
            "Which component is used to display text in Swing?",
            "Which layout arranges components in rows and columns?",
            "Which keyword is used for inheritance in Java?",
            "Which method compares String values?",
            "Which component allows selection from options?",
            "Which class is used to create a window in Swing?",
            "Which keyword prevents inheritance?",
            "Which data type stores whole numbers?",
            "Which method gets text from a JTextField?",
            "Which class groups radio buttons?",
            "Which block is used to handle exceptions?",
            "Which operator checks equality for primitive values?",
            "Which collection stores unique values?",
            "Which keyword refers to the current object?",
            "Which method stops a Swing Timer?",
            "Which class displays message dialogs?",
            "Which method adds a component to a container?",
            "Which keyword is used to return a value?",
            "Which is the parent class of all Java classes?",
            "Which method gets the length of a String?",
            "Which component is used for password input?",
            "Which keyword is used to define a constant?",
            "Which package provides date and time utilities?"
    };

    private String[][] options = {
            { "Java", "Python", "C", "HTML" },
            { "class", "new", "void", "static" },
            { "start()", "main()", "run()", "init()" },
            { "JLabel", "JButton", "JTextField", "JFrame" },
            { "java.io", "java.net", "javax.swing", "java.sql" },
            { "make", "object", "new", "create" },
            { "int", "boolean", "char", "String" },
            { "JLabel", "JButton", "JRadioButton", "JFrame" },
            { "BorderLayout", "GridLayout", "FlowLayout", "CardLayout" },
            { "implements", "extends", "inherits", "super" },
            { "==", "compare", "equals()", "same()" },
            { "JTextArea", "JRadioButton", "JLabel", "JPanel" },
            { "JFrame", "JButton", "JLabel", "JTextField" },
            { "static", "private", "final", "void" },
            { "int", "double", "boolean", "String" },
            { "setText()", "getText()", "readText()", "text()" },
            { "ButtonGroup", "JPanel", "JFrame", "JLabel" },
            { "catch", "throw", "try", "All of these" },
            { "=", "==", "equals()", "!=" },
            { "ArrayList", "HashSet", "LinkedList", "Array" },
            { "super", "this", "self", "current" },
            { "pause()", "stop()", "end()", "finish()" },
            { "JOptionPane", "JLabel", "JPanel", "JFrame" },
            { "insert()", "append()", "add()", "put()" },
            { "send", "return", "break", "continue" },
            { "Main", "Object", "Class", "System" },
            { "size()", "count()", "length()", "getLength()" },
            { "JTextField", "JPasswordField", "JLabel", "JButton" },
            { "const", "static", "final", "fixed" },
            { "java.time", "java.swing", "java.text", "java.awt" }
    };

    // Correct option indexes: 0 = first option, 1 = second, etc.
    private int[] correctAnswers = {
            0, 0, 1, 1, 2, 2, 1, 0, 1, 1,
            2, 1, 0, 2, 0, 1, 0, 3, 1, 1,
            1, 1, 0, 2, 1, 1, 2, 1, 2, 0
    };

    private int[] selectedAnswers = new int[30];

    // ---------------- GUI VARIABLES ----------------

    private CardLayout cardLayout;
    private JPanel mainPanel;

    private JTextField loginUsernameField;
    private JPasswordField loginPasswordField;

    private JTextField displayNameField;
    private JPasswordField profilePasswordField;

    private JLabel questionLabel;
    private JLabel questionNumberLabel;
    private JLabel timerLabel;

    private JRadioButton[] optionButtons;
    private ButtonGroup optionGroup;

    private JButton previousButton;
    private JButton nextButton;

    private javax.swing.Timer examTimer;

    private int currentQuestion = 0;
    private int timeLeft = 30 * 60;

    private long examStartTime;

    private boolean examStarted = false;
    private boolean examSubmitted = false;

    // ---------------- CONSTRUCTOR ----------------

    public Main() {

        setTitle("Online Examination System");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        for (int i = 0; i < selectedAnswers.length; i++) {
            selectedAnswers[i] = -1;
        }

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createLoginScreen(), "LOGIN");
        mainPanel.add(createProfileScreen(), "PROFILE");
        mainPanel.add(createExamScreen(), "EXAM");

        add(mainPanel);

        cardLayout.show(mainPanel, "LOGIN");

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                handleWindowClose();
            }
        });

        setVisible(true);
    }

    // ---------------- LOGIN SCREEN ----------------

    private JPanel createLoginScreen() {

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(235, 242, 250));

        JPanel box = new JPanel(new GridLayout(0, 1, 10, 10));
        box.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JLabel title = new JLabel("Online Examination System");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        loginUsernameField = new JTextField();
        loginPasswordField = new JPasswordField();

        JButton loginButton = new JButton("Login");

        box.add(title);
        box.add(new JLabel("Username:"));
        box.add(loginUsernameField);
        box.add(new JLabel("Password:"));
        box.add(loginPasswordField);
        box.add(loginButton);

        loginButton.addActionListener(e -> login());

        panel.add(box);

        return panel;
    }

    private void login() {

        String enteredUsername = loginUsernameField.getText().trim();
        String enteredPassword = new String(loginPasswordField.getPassword());

        if (username.equals(enteredUsername)
                && password.equals(enteredPassword)) {

            displayNameField.setText(displayName);
            profilePasswordField.setText("");

            cardLayout.show(mainPanel, "PROFILE");

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid username or password!",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // ---------------- PROFILE SCREEN ----------------

    private JPanel createProfileScreen() {

        JPanel panel = new JPanel(new GridBagLayout());

        JPanel box = new JPanel(new GridLayout(0, 1, 10, 10));
        box.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel title = new JLabel("Update Profile");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        displayNameField = new JTextField(displayName);
        profilePasswordField = new JPasswordField();

        JButton updateButton = new JButton("Update Profile");
        JButton startButton = new JButton("Start Exam");
        JButton logoutButton = new JButton("Logout");

        box.add(title);
        box.add(new JLabel("Display Name:"));
        box.add(displayNameField);
        box.add(new JLabel("New Password:"));
        box.add(profilePasswordField);
        box.add(updateButton);
        box.add(startButton);
        box.add(logoutButton);

        updateButton.addActionListener(e -> updateProfile());

        startButton.addActionListener(e -> startExam());

        logoutButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "LOGIN");
        });

        panel.add(box);

        return panel;
    }

    private void updateProfile() {

        String newName = displayNameField.getText().trim();
        String newPassword = new String(profilePasswordField.getPassword());

        if (newName.isEmpty() || newPassword.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter display name and new password.");

            return;
        }

        displayName = newName;
        password = newPassword;

        profilePasswordField.setText("");

        JOptionPane.showMessageDialog(
                this,
                "Profile updated successfully!");
    }

    // ---------------- EXAM SCREEN ----------------

    private JPanel createExamScreen() {

        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel topPanel = new JPanel(new BorderLayout());

        questionNumberLabel = new JLabel("Question 1 of 30");
        questionNumberLabel.setFont(
                new Font("Arial", Font.BOLD, 16));

        timerLabel = new JLabel("Time Left: 30:00");
        timerLabel.setFont(
                new Font("Arial", Font.BOLD, 16));

        topPanel.add(questionNumberLabel, BorderLayout.WEST);
        topPanel.add(timerLabel, BorderLayout.EAST);

        panel.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(
                new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        questionLabel = new JLabel();
        questionLabel.setFont(
                new Font("Arial", Font.BOLD, 18));

        centerPanel.add(questionLabel);
        centerPanel.add(Box.createVerticalStrut(25));

        optionButtons = new JRadioButton[4];
        optionGroup = new ButtonGroup();

        for (int i = 0; i < 4; i++) {

            optionButtons[i] = new JRadioButton();
            optionButtons[i].setFont(
                    new Font("Arial", Font.PLAIN, 16));

            optionGroup.add(optionButtons[i]);

            centerPanel.add(optionButtons[i]);
            centerPanel.add(Box.createVerticalStrut(12));
        }

        panel.add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout());

        previousButton = new JButton("Previous");
        nextButton = new JButton("Next");
        JButton submitButton = new JButton("Submit Exam");

        bottomPanel.add(previousButton);
        bottomPanel.add(nextButton);
        bottomPanel.add(submitButton);

        panel.add(bottomPanel, BorderLayout.SOUTH);

        previousButton.addActionListener(e -> {
            saveAnswer();

            if (currentQuestion > 0) {
                currentQuestion--;
                showQuestion();
            }
        });

        nextButton.addActionListener(e -> {
            saveAnswer();

            if (currentQuestion < questions.length - 1) {
                currentQuestion++;
                showQuestion();
            }
        });

        submitButton.addActionListener(e -> {

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to submit the exam?",
                    "Confirm Submission",
                    JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                submitExam();
            }
        });

        return panel;
    }

    // ---------------- START EXAM ----------------

    private void startExam() {

        currentQuestion = 0;
        timeLeft = 30 * 60;

        examStarted = true;
        examSubmitted = false;

        examStartTime = System.currentTimeMillis();

        for (int i = 0; i < selectedAnswers.length; i++) {
            selectedAnswers[i] = -1;
        }

        timerLabel.setText("Time Left: 30:00");

        showQuestion();

        if (examTimer != null) {
            examTimer.stop();
        }

        examTimer = new javax.swing.Timer(1000, e -> updateTimer());
        examTimer.start();

        cardLayout.show(mainPanel, "EXAM");
    }

    // ---------------- DISPLAY QUESTION ----------------

    private void showQuestion() {

        questionNumberLabel.setText(
                "Question " + (currentQuestion + 1)
                        + " of " + questions.length);

        questionLabel.setText(
                "<html><div style='width:650px'>"
                        + questions[currentQuestion]
                        + "</div></html>");

        optionGroup.clearSelection();

        for (int i = 0; i < 4; i++) {

            optionButtons[i].setText(options[currentQuestion][i]);

            if (selectedAnswers[currentQuestion] == i) {
                optionButtons[i].setSelected(true);
            }
        }

        previousButton.setEnabled(currentQuestion > 0);

        nextButton.setEnabled(
                currentQuestion < questions.length - 1);
    }

    private void saveAnswer() {

        for (int i = 0; i < 4; i++) {

            if (optionButtons[i].isSelected()) {
                selectedAnswers[currentQuestion] = i;
                return;
            }
        }

        selectedAnswers[currentQuestion] = -1;
    }

    // ---------------- TIMER ----------------

    private void updateTimer() {

        timeLeft--;

        int minutes = timeLeft / 60;
        int seconds = timeLeft % 60;

        timerLabel.setText(
                String.format(
                        "Time Left: %02d:%02d",
                        minutes,
                        seconds));

        if (timeLeft <= 0) {

            examTimer.stop();

            JOptionPane.showMessageDialog(
                    this,
                    "Time is over! Your exam will be submitted.");

            submitExam();
        }
    }

    // ---------------- SUBMIT EXAM ----------------

    private void submitExam() {

        if (examSubmitted) {
            return;
        }

        saveAnswer();

        examSubmitted = true;
        examStarted = false;

        if (examTimer != null) {
            examTimer.stop();
        }

        int correct = 0;
        int incorrect = 0;
        int unanswered = 0;

        for (int i = 0; i < questions.length; i++) {

            if (selectedAnswers[i] == -1) {

                unanswered++;

            } else if (selectedAnswers[i] == correctAnswers[i]) {

                correct++;

            } else {

                incorrect++;
            }
        }

        int timeTaken = (int) ((System.currentTimeMillis() - examStartTime) / 1000);

        showResult(correct, incorrect, unanswered, timeTaken);
    }

    // ---------------- RESULT SCREEN ----------------

    private void showResult(
            int correct,
            int incorrect,
            int unanswered,
            int timeTaken) {

        JPanel resultPanel = new JPanel(new GridBagLayout());

        JPanel box = new JPanel(new GridLayout(0, 1, 10, 10));
        box.setBorder(
                BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel title = new JLabel("Exam Result");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        int minutes = timeTaken / 60;
        int seconds = timeTaken % 60;

        JLabel scoreLabel = new JLabel(
                "Score: " + correct + " / " + questions.length);

        JLabel correctLabel = new JLabel(
                "Correct Answers: " + correct);

        JLabel incorrectLabel = new JLabel(
                "Incorrect Answers: " + incorrect);

        JLabel unansweredLabel = new JLabel(
                "Unanswered Questions: " + unanswered);

        JLabel timeLabel = new JLabel(
                String.format(
                        "Time Taken: %02d:%02d",
                        minutes,
                        seconds));

        JButton logoutButton = new JButton("Logout");

        box.add(title);
        box.add(scoreLabel);
        box.add(correctLabel);
        box.add(incorrectLabel);
        box.add(unansweredLabel);
        box.add(timeLabel);
        box.add(logoutButton);

        logoutButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "LOGIN");
        });

        resultPanel.add(box);

        mainPanel.add(resultPanel, "RESULT");

        cardLayout.show(mainPanel, "RESULT");
    }

    // ---------------- WINDOW CLOSE ----------------

    private void handleWindowClose() {

        if (examStarted && !examSubmitted) {

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to quit?",
                    "Quit Exam",
                    JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {

                if (examTimer != null) {
                    examTimer.stop();
                }

                dispose();
            }

        } else {

            dispose();
        }
    }

    // ---------------- MAIN METHOD ----------------

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new Main());
    }
}
