import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

public class VotingUI extends JFrame {

    // =========================
    // MODERN LIGHT THEME
    // =========================
    private final Color BG = new Color(244, 247, 252);
    private final Color CARD = Color.WHITE;

    private final Color PRIMARY = new Color(37, 99, 235);
    private final Color PRIMARY_DARK = new Color(29, 78, 216);

    private final Color SUCCESS = new Color(22, 163, 74);
    private final Color SUCCESS_DARK = new Color(21, 128, 61);

    private final Color DANGER = new Color(220, 38, 38);
    private final Color ADMIN = new Color(124, 58, 237);

    private final Color TEXT = new Color(30, 41, 59);
    private final Color MUTED = new Color(100, 116, 139);
    private final Color BORDER = new Color(226, 232, 240);

    private JPanel mainPanel;

    private String currentVoterId = "";
    private String currentVoterName = "";

    // =========================
    // CONSTRUCTOR
    // =========================
    public VotingUI() {

        setTitle("Online Voting System");
        setSize(950, 680);
        setMinimumSize(new Dimension(900, 620));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        showMainMenu();
    }

    // =========================================================
    // MAIN MENU
    // =========================================================
    private void showMainMenu() {

        JPanel background = createBackground();

        JPanel card = createCard(650, 555);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        // ICON
        VoteIcon icon = new VoteIcon(72, PRIMARY);
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(icon);
        card.add(Box.createVerticalStrut(18));

        JLabel title = createTitle("ONLINE VOTING SYSTEM");

        JLabel subtitle = createSubtitle(
                "Secure • Simple • Transparent"
        );

        card.add(title);
        card.add(Box.createVerticalStrut(8));
        card.add(subtitle);
        card.add(Box.createVerticalStrut(35));

        JLabel welcome = new JLabel(
                "Choose an option to continue",
                SwingConstants.CENTER
        );

        welcome.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        welcome.setForeground(MUTED);
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(welcome);
        card.add(Box.createVerticalStrut(20));

        JButton registerButton =
                createRoundedButton(
                        "VOTER REGISTRATION",
                        PRIMARY
                );

        JButton loginButton =
                createRoundedButton(
                        "VOTER LOGIN",
                        SUCCESS
                );

        JButton adminButton =
                createRoundedButton(
                        "ADMIN LOGIN",
                        ADMIN
                );

        JButton exitButton =
                createRoundedButton(
                        "EXIT",
                        DANGER
                );

        card.add(registerButton);
        card.add(Box.createVerticalStrut(12));

        card.add(loginButton);
        card.add(Box.createVerticalStrut(12));

        card.add(adminButton);
        card.add(Box.createVerticalStrut(12));

        card.add(exitButton);

        registerButton.addActionListener(
                e -> showRegistrationPage()
        );

        loginButton.addActionListener(
                e -> showLoginPage()
        );

        adminButton.addActionListener(
                e -> showAdminLogin()
        );

        exitButton.addActionListener(
                e -> System.exit(0)
        );

        card.add(Box.createVerticalStrut(20));

        JLabel footer = new JLabel(
                "Object-Oriented Programming Project",
                SwingConstants.CENTER
        );

        footer.setFont(
                new Font("Arial", Font.PLAIN, 12)
        );

        footer.setForeground(MUTED);
        footer.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(footer);

        background.add(card);
        setPage(background);
    }

    // =========================================================
    // REGISTRATION
    // =========================================================
    private void showRegistrationPage() {

        JPanel background = createBackground();

        JPanel card = createCard(650, 560);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel title =
                createPageTitle("VOTER REGISTRATION");

        JLabel info =
                createSubtitle(
                        "Create your secure voter account"
                );

        card.add(title);
        card.add(Box.createVerticalStrut(8));
        card.add(info);
        card.add(Box.createVerticalStrut(28));

        // VOTER ID
        card.add(createFieldLabel("Voter ID"));
        card.add(Box.createVerticalStrut(6));

        JTextField voterIdField =
                createTextField("Enter your voter ID");

        card.add(voterIdField);

        card.add(Box.createVerticalStrut(17));

        // NAME
        card.add(createFieldLabel("Full Name"));
        card.add(Box.createVerticalStrut(6));

        JTextField nameField =
                createTextField("Enter your full name");

        card.add(nameField);

        card.add(Box.createVerticalStrut(17));

        // PASSWORD
        card.add(createFieldLabel("Password"));
        card.add(Box.createVerticalStrut(6));

        JPasswordField passwordField =
                createPasswordField("Enter your password");

        card.add(passwordField);

        card.add(Box.createVerticalStrut(25));

        JButton registerButton =
                createRoundedButton(
                        "REGISTER ACCOUNT",
                        SUCCESS
                );

        JButton backButton =
                createOutlineButton("← BACK TO HOME");

        card.add(registerButton);
        card.add(Box.createVerticalStrut(12));
        card.add(backButton);

        // REGISTER ACTION
        registerButton.addActionListener(e -> {

            String voterId =
                    voterIdField.getText().trim();

            String name =
                    nameField.getText().trim();

            String password =
                    new String(
                            passwordField.getPassword()
                    );

            if (voterId.isEmpty()
                    || name.isEmpty()
                    || password.isEmpty()) {

                showMessage(
                        "Please fill all fields.",
                        "Registration",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            boolean registered =
                    OnlineVotingSystem.registerVoter(
                            voterId,
                            name,
                            password
                    );

            if (registered) {

                showMessage(
                        "Registration Successful!\n\n"
                                + "Voter ID: " + voterId
                                + "\nName: " + name,
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                showLoginPage();

            } else {

                showMessage(
                        "Registration failed.\n\n"
                                + "Voter ID may already exist.",
                        "Registration Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        backButton.addActionListener(
                e -> showMainMenu()
        );

        background.add(card);
        setPage(background);
    }

    // =========================================================
    // LOGIN
    // =========================================================
    private void showLoginPage() {

        JPanel background = createBackground();

        JPanel card = createCard(650, 500);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        UserIcon userIcon =
                new UserIcon(65, PRIMARY);

        userIcon.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        card.add(userIcon);
        card.add(Box.createVerticalStrut(15));

        JLabel title =
                createPageTitle("VOTER LOGIN");

        JLabel info =
                createSubtitle(
                        "Login securely to cast your vote"
                );

        card.add(title);
        card.add(Box.createVerticalStrut(8));
        card.add(info);
        card.add(Box.createVerticalStrut(28));

        // VOTER ID
        card.add(createFieldLabel("Voter ID"));
        card.add(Box.createVerticalStrut(6));

        JTextField voterIdField =
                createTextField("Enter your voter ID");

        card.add(voterIdField);

        card.add(Box.createVerticalStrut(17));

        // PASSWORD
        card.add(createFieldLabel("Password"));
        card.add(Box.createVerticalStrut(6));

        JPasswordField passwordField =
                createPasswordField("Enter your password");

        card.add(passwordField);

        card.add(Box.createVerticalStrut(25));

        JButton loginButton =
                createRoundedButton(
                        "LOGIN & CONTINUE",
                        PRIMARY
                );

        JButton backButton =
                createOutlineButton("← BACK TO HOME");

        card.add(loginButton);
        card.add(Box.createVerticalStrut(12));
        card.add(backButton);

        loginButton.addActionListener(e -> {

            String voterId =
                    voterIdField.getText().trim();

            String password =
                    new String(
                            passwordField.getPassword()
                    );

            if (voterId.isEmpty()
                    || password.isEmpty()) {

                showMessage(
                        "Please enter Voter ID and Password.",
                        "Login",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            Voter voter =
                    OnlineVotingSystem.loginVoter(
                            voterId,
                            password
                    );

            if (voter != null) {

                currentVoterId =
                        voter.getVoterId();

                currentVoterName =
                        voter.getName();

                showMessage(
                        "Login Successful!\n\n"
                                + "Welcome "
                                + currentVoterName
                                + "!",
                        "Welcome",
                        JOptionPane.INFORMATION_MESSAGE
                );

                showVotingPage();

            } else {

                showMessage(
                        "Invalid Voter ID or Password.",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        backButton.addActionListener(
                e -> showMainMenu()
        );

        background.add(card);
        setPage(background);
    }

    // =========================================================
    // VOTING PAGE
    // =========================================================
    private void showVotingPage() {

        JPanel background = createBackground();

        JPanel card = createCard(800, 590);
        card.setLayout(
                new BoxLayout(card, BoxLayout.Y_AXIS)
        );

        JLabel title =
                createPageTitle("CAST YOUR VOTE");

        JLabel welcome =
                createSubtitle(
                        "Welcome, " + currentVoterName
                );

        card.add(title);
        card.add(Box.createVerticalStrut(8));
        card.add(welcome);
        card.add(Box.createVerticalStrut(28));

        if (OnlineVotingSystem.hasVoted(
                currentVoterId)) {

            JPanel warning =
                    createStatusPanel(
                            "✓ You have already voted.",
                            DANGER
                    );

            card.add(warning);
            card.add(Box.createVerticalStrut(30));

            JButton back =
                    createOutlineButton(
                            "← BACK TO HOME"
                    );

            card.add(back);

            back.addActionListener(
                    e -> showMainMenu()
            );

        } else {

            JLabel instruction =
                    new JLabel(
                            "Select one candidate",
                            SwingConstants.CENTER
                    );

            instruction.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            16
                    )
            );

            instruction.setForeground(TEXT);
            instruction.setAlignmentX(
                    Component.CENTER_ALIGNMENT
            );

            card.add(instruction);
            card.add(Box.createVerticalStrut(22));

            JPanel candidates =
                    new JPanel(
                            new GridLayout(
                                    1,
                                    3,
                                    18,
                                    0
                            )
                    );

            candidates.setOpaque(false);
            candidates.setMaximumSize(
                    new Dimension(720, 260)
            );

            candidates.add(
                    createCandidateCard(
                            "Candidate A",
                            "A",
                            PRIMARY
                    )
            );

            candidates.add(
                    createCandidateCard(
                            "Candidate B",
                            "B",
                            ADMIN
                    )
            );

            candidates.add(
                    createCandidateCard(
                            "Candidate C",
                            "C",
                            SUCCESS
                    )
            );

            card.add(candidates);
            card.add(Box.createVerticalStrut(25));

            JButton back =
                    createOutlineButton("← BACK");

            card.add(back);

            back.addActionListener(
                    e -> showMainMenu()
            );
        }

        background.add(card);
        setPage(background);
    }

    // =========================================================
    // CANDIDATE CARD
    // =========================================================
    private JPanel createCandidateCard(
            String candidate,
            String letter,
            Color color) {

        JPanel panel = new JPanel();

        panel.setBackground(Color.WHITE);

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        panel.setBorder(
                new CompoundBorder(
                        new LineBorder(
                                BORDER,
                                1,
                                true
                        ),
                        new EmptyBorder(
                                18,
                                15,
                                18,
                                15
                        )
                )
        );

        CandidateIcon icon =
                new CandidateIcon(
                        72,
                        color,
                        letter
                );

        icon.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel name =
                new JLabel(candidate);

        name.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        name.setForeground(TEXT);
        name.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel role =
                new JLabel("Election Candidate");

        role.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        role.setForeground(MUTED);
        role.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JButton vote =
                createSmallRoundedButton(
                        "VOTE",
                        color
                );

        vote.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        vote.addActionListener(
                e -> confirmVote(candidate)
        );

        panel.add(icon);
        panel.add(Box.createVerticalStrut(12));
        panel.add(name);
        panel.add(Box.createVerticalStrut(5));
        panel.add(role);
        panel.add(Box.createVerticalGlue());
        panel.add(vote);

        return panel;
    }

    // =========================================================
    // CONFIRM VOTE
    // =========================================================
    private void confirmVote(String candidate) {

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to vote for "
                                + candidate + "?",
                        "Confirm Your Vote",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

        if (choice == JOptionPane.YES_OPTION) {

            boolean success =
                    OnlineVotingSystem.castVote(
                            currentVoterId,
                            candidate
                    );

            if (success) {

                showMessage(
                        "Vote Cast Successfully!\n\n"
                                + "Your vote has been recorded.",
                        "Vote Successful",
                        JOptionPane.INFORMATION_MESSAGE
                );

                currentVoterId = "";
                currentVoterName = "";

                showMainMenu();

            } else {

                showMessage(
                        "You have already voted or "
                                + "the vote could not be recorded.",
                        "Vote Failed",
                        JOptionPane.ERROR_MESSAGE
                );

                showVotingPage();
            }
        }
    }

    // =========================================================
    // ADMIN LOGIN
    // =========================================================
    private void showAdminLogin() {

        JPanel background = createBackground();

        JPanel card = createCard(650, 500);
        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        AdminIcon adminIcon =
                new AdminIcon(65, ADMIN);

        adminIcon.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        card.add(adminIcon);
        card.add(Box.createVerticalStrut(15));

        JLabel title =
                createPageTitle("ADMIN LOGIN");

        JLabel info =
                createSubtitle(
                        "Secure administrator access"
                );

        card.add(title);
        card.add(Box.createVerticalStrut(8));
        card.add(info);
        card.add(Box.createVerticalStrut(28));

        // USERNAME
        card.add(createFieldLabel("Username"));
        card.add(Box.createVerticalStrut(6));

        JTextField usernameField =
                createTextField("Enter admin username");

        card.add(usernameField);

        card.add(Box.createVerticalStrut(17));

        // PASSWORD
        card.add(createFieldLabel("Password"));
        card.add(Box.createVerticalStrut(6));

        JPasswordField passwordField =
                createPasswordField("Enter admin password");

        card.add(passwordField);

        card.add(Box.createVerticalStrut(25));

        JButton loginButton =
                createRoundedButton(
                        "ADMIN LOGIN",
                        ADMIN
                );

        JButton backButton =
                createOutlineButton(
                        "← BACK TO HOME"
                );

        card.add(loginButton);
        card.add(Box.createVerticalStrut(12));
        card.add(backButton);

        loginButton.addActionListener(e -> {

            String username =
                    usernameField.getText().trim();

            String password =
                    new String(
                            passwordField.getPassword()
                    );

            if (OnlineVotingSystem.adminLogin(
                    username,
                    password)) {

                showResults();

            } else {

                showMessage(
                        "Invalid Admin Username or Password.",
                        "Admin Login Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        backButton.addActionListener(
                e -> showMainMenu()
        );

        background.add(card);
        setPage(background);
    }

    // =========================================================
    // RESULTS
    // =========================================================
    private void showResults() {

        JPanel background =
                createBackground();

        JPanel card =
                createCard(800, 590);

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel title =
                createPageTitle("ELECTION RESULTS");

        JLabel subtitle =
                createSubtitle(
                        "Current voting statistics"
                );

        card.add(title);
        card.add(Box.createVerticalStrut(8));
        card.add(subtitle);
        card.add(Box.createVerticalStrut(25));

        int votesA =
                OnlineVotingSystem.getCandidateAVotes();

        int votesB =
                OnlineVotingSystem.getCandidateBVotes();

        int votesC =
                OnlineVotingSystem.getCandidateCVotes();

        int total =
                OnlineVotingSystem.getTotalVotes();

        card.add(
                resultCard(
                        "Candidate A",
                        votesA,
                        total,
                        PRIMARY
                )
        );

        card.add(Box.createVerticalStrut(12));

        card.add(
                resultCard(
                        "Candidate B",
                        votesB,
                        total,
                        ADMIN
                )
        );

        card.add(Box.createVerticalStrut(12));

        card.add(
                resultCard(
                        "Candidate C",
                        votesC,
                        total,
                        SUCCESS
                )
        );

        card.add(Box.createVerticalStrut(20));

        JPanel totalPanel =
                new JPanel(
                        new BorderLayout()
                );

        totalPanel.setMaximumSize(
                new Dimension(700, 65)
        );

        totalPanel.setBackground(
                new Color(239, 246, 255)
        );

        totalPanel.setBorder(
                new CompoundBorder(
                        new LineBorder(
                                new Color(191, 219, 254),
                                1,
                                true
                        ),
                        new EmptyBorder(
                                10,
                                20,
                                10,
                                20
                        )
                )
        );

        JLabel totalText =
                new JLabel("TOTAL VOTES");

        totalText.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        totalText.setForeground(MUTED);

        JLabel totalValue =
                new JLabel(
                        String.valueOf(total)
                );

        totalValue.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        25
                )
        );

        totalValue.setForeground(
                PRIMARY
        );

        totalPanel.add(
                totalText,
                BorderLayout.WEST
        );

        totalPanel.add(
                totalValue,
                BorderLayout.EAST
        );

        card.add(totalPanel);

        card.add(Box.createVerticalStrut(20));

        JButton back =
                createOutlineButton(
                        "← BACK TO HOME"
                );

        card.add(back);

        back.addActionListener(
                e -> showMainMenu()
        );

        background.add(card);
        setPage(background);
    }

    // =========================================================
    // RESULT CARD WITH PERCENTAGE
    // =========================================================
    private JPanel resultCard(
            String candidate,
            int votes,
            int total,
            Color color) {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                15,
                                0
                        )
                );

        panel.setBackground(Color.WHITE);

        panel.setMaximumSize(
                new Dimension(700, 85)
        );

        panel.setBorder(
                new CompoundBorder(
                        new LineBorder(
                                BORDER,
                                1,
                                true
                        ),
                        new EmptyBorder(
                                12,
                                18,
                                12,
                                18
                        )
                )
        );

        double percentage = 0;

        if (total > 0) {

            percentage =
                    ((double) votes / total)
                            * 100;
        }

        JPanel left =
                new JPanel();

        left.setOpaque(false);

        left.setLayout(
                new BoxLayout(
                        left,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel name =
                new JLabel(candidate);

        name.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        name.setForeground(TEXT);

        JLabel voteText =
                new JLabel(
                        votes + " votes"
                );

        voteText.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        voteText.setForeground(MUTED);

        left.add(name);
        left.add(Box.createVerticalStrut(4));
        left.add(voteText);

        panel.add(
                left,
                BorderLayout.WEST
        );

        // PROGRESS BAR
        JProgressBar progress =
                new JProgressBar(
                        0,
                        100
                );

        progress.setValue(
                (int) Math.round(
                        percentage
                )
        );

        progress.setStringPainted(false);

        progress.setForeground(color);

        progress.setBackground(
                new Color(241, 245, 249)
        );

        progress.setBorderPainted(false);

        panel.add(
                progress,
                BorderLayout.CENTER
        );

        JLabel percent =
                new JLabel(
                        String.format(
                                "%.1f%%",
                                percentage
                        )
                );

        percent.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        percent.setForeground(color);

        panel.add(
                percent,
                BorderLayout.EAST
        );

        return panel;
    }

    // =========================================================
    // BACKGROUND
    // =========================================================
    private JPanel createBackground() {

        JPanel panel =
                new JPanel(
                        new GridBagLayout()
                );

        panel.setBackground(BG);

        return panel;
    }

    // =========================================================
    // CARD
    // =========================================================
    private JPanel createCard(
            int width,
            int height) {

        JPanel card =
                new JPanel();

        card.setPreferredSize(
                new Dimension(
                        width,
                        height
                )
        );

        card.setBackground(CARD);

        card.setBorder(
                new CompoundBorder(
                        new LineBorder(
                                BORDER,
                                1,
                                true
                        ),
                        new EmptyBorder(
                                30,
                                45,
                                30,
                                45
                        )
                )
        );

        return card;
    }

    // =========================================================
    // TITLE
    // =========================================================
    private JLabel createTitle(
            String text) {

        JLabel label =
                new JLabel(
                        text,
                        SwingConstants.CENTER
                );

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        31
                )
        );

        label.setForeground(PRIMARY);

        label.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        return label;
    }

    // =========================================================
    // PAGE TITLE
    // =========================================================
    private JLabel createPageTitle(
            String text) {

        JLabel label =
                new JLabel(
                        text,
                        SwingConstants.CENTER
                );

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        27
                )
        );

        label.setForeground(TEXT);

        label.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        return label;
    }

    // =========================================================
    // SUBTITLE
    // =========================================================
    private JLabel createSubtitle(
            String text) {

        JLabel label =
                new JLabel(
                        text,
                        SwingConstants.CENTER
                );

        label.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        label.setForeground(MUTED);

        label.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        return label;
    }

    // =========================================================
    // FIELD LABEL
    // =========================================================
    private JLabel createFieldLabel(
            String text) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        label.setForeground(TEXT);

        label.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        return label;
    }

    // =========================================================
    // TEXT FIELD
    // =========================================================
    private JTextField createTextField(
            String tooltip) {

        JTextField field =
                new JTextField();

        field.setMaximumSize(
                new Dimension(
                        520,
                        45
                )
        );

        field.setPreferredSize(
                new Dimension(
                        520,
                        45
                )
        );

        field.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        field.setForeground(TEXT);
        field.setBackground(Color.WHITE);
        field.setCaretColor(PRIMARY);

        field.setToolTipText(tooltip);

        field.setBorder(
                new CompoundBorder(
                        new LineBorder(
                                new Color(
                                        203,
                                        213,
                                        225
                                ),
                                1,
                                true
                        ),
                        new EmptyBorder(
                                8,
                                14,
                                8,
                                14
                        )
                )
        );

        field.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        return field;
    }

    // =========================================================
    // PASSWORD FIELD
    // =========================================================
    private JPasswordField createPasswordField(
            String tooltip) {

        JPasswordField field =
                new JPasswordField();

        field.setMaximumSize(
                new Dimension(
                        520,
                        45
                )
        );

        field.setPreferredSize(
                new Dimension(
                        520,
                        45
                )
        );

        field.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        field.setForeground(TEXT);
        field.setBackground(Color.WHITE);
        field.setCaretColor(PRIMARY);

        field.setToolTipText(tooltip);

        field.setBorder(
                new CompoundBorder(
                        new LineBorder(
                                new Color(
                                        203,
                                        213,
                                        225
                                ),
                                1,
                                true
                        ),
                        new EmptyBorder(
                                8,
                                14,
                                8,
                                14
                        )
                )
        );

        field.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        return field;
    }

    // =========================================================
    // ROUNDED BUTTON
    // =========================================================
    private JButton createRoundedButton(
            String text,
            Color color) {

        RoundedButton button =
                new RoundedButton(
                        text,
                        color
                );

        button.setMaximumSize(
                new Dimension(
                        520,
                        48
                )
        );

        button.setPreferredSize(
                new Dimension(
                        520,
                        48
                )
        );

        button.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        return button;
    }

    // =========================================================
    // SMALL ROUNDED BUTTON
    // =========================================================
    private JButton createSmallRoundedButton(
            String text,
            Color color) {

        RoundedButton button =
                new RoundedButton(
                        text,
                        color
                );

        button.setMaximumSize(
                new Dimension(
                        180,
                        42
                )
        );

        button.setPreferredSize(
                new Dimension(
                        180,
                        42
                )
        );

        button.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        return button;
    }

    // =========================================================
    // OUTLINE BUTTON
    // =========================================================
    private JButton createOutlineButton(
            String text) {

        JButton button =
                new JButton(text);

        button.setMaximumSize(
                new Dimension(
                        520,
                        44
                )
        );

        button.setPreferredSize(
                new Dimension(
                        520,
                        44
                )
        );

        button.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        button.setForeground(PRIMARY);
        button.setBackground(Color.WHITE);

        button.setFocusPainted(false);
        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setBorder(
                new LineBorder(
                        new Color(
                                147,
                                197,
                                253
                        ),
                        1,
                        true
                )
        );

        return button;
    }

    // =========================================================
    // STATUS PANEL
    // =========================================================
    private JPanel createStatusPanel(
            String text,
            Color color) {

        JPanel panel =
                new JPanel(
                        new GridBagLayout()
                );

        panel.setMaximumSize(
                new Dimension(
                        650,
                        70
                )
        );

        panel.setBackground(
                new Color(
                        254,
                        242,
                        242
                )
        );

        panel.setBorder(
                new LineBorder(
                        new Color(
                                254,
                                202,
                                202
                        ),
                        1,
                        true
                )
        );

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        label.setForeground(color);

        panel.add(label);

        return panel;
    }

    // =========================================================
    // ROUNDED BUTTON CLASS
    // =========================================================
    private static class RoundedButton
            extends JButton {

        private Color normalColor;
        private Color hoverColor;

        public RoundedButton(
                String text,
                Color color) {

            super(text);

            normalColor = color;

            hoverColor =
                    color.darker();

            setForeground(Color.WHITE);

            setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            14
                    )
            );

            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);

            setOpaque(false);

            setCursor(
                    new Cursor(
                            Cursor.HAND_CURSOR
                    )
            );

            addMouseListener(
                    new MouseAdapter() {

                        @Override
                        public void mouseEntered(
                                MouseEvent e) {

                            normalColor =
                                    hoverColor;

                            repaint();
                        }

                        @Override
                        public void mouseExited(
                                MouseEvent e) {

                            normalColor =
                                    color;

                            repaint();
                        }
                    }
            );
        }

        @Override
        protected void paintComponent(
                Graphics g) {

            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(normalColor);

            g2.fill(
                    new RoundRectangle2D.Double(
                            0,
                            0,
                            getWidth(),
                            getHeight(),
                            18,
                            18
                    )
            );

            super.paintComponent(g);

            g2.dispose();
        }
    }

    // =========================================================
    // VOTE ICON
    // =========================================================
    private static class VoteIcon
            extends JPanel {

        private int size;
        private Color color;

        VoteIcon(
                int size,
                Color color) {

            this.size = size;
            this.color = color;

            setPreferredSize(
                    new Dimension(
                            size,
                            size
                    )
            );

            setOpaque(false);
        }

        @Override
        protected void paintComponent(
                Graphics g) {

            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(
                    new Color(
                            219,
                            234,
                            254
                    )
            );

            g2.fillOval(
                    0,
                    0,
                    size,
                    size
            );

            g2.setColor(color);

            g2.fillRoundRect(
                    18,
                    28,
                    size - 36,
                    35,
                    8,
                    8
            );

            g2.setColor(Color.WHITE);

            g2.setStroke(
                    new BasicStroke(
                            4,
                            BasicStroke.CAP_ROUND,
                            BasicStroke.JOIN_ROUND
                    )
            );

            g2.drawLine(
                    27,
                    45,
                    35,
                    54
            );

            g2.drawLine(
                    35,
                    54,
                    52,
                    35
            );

            g2.dispose();
        }
    }

    // =========================================================
    // USER ICON
    // =========================================================
    private static class UserIcon
            extends JPanel {

        private int size;
        private Color color;

        UserIcon(
                int size,
                Color color) {

            this.size = size;
            this.color = color;

            setPreferredSize(
                    new Dimension(
                            size,
                            size
                    )
            );

            setOpaque(false);
        }

        @Override
        protected void paintComponent(
                Graphics g) {

            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(
                    new Color(
                            219,
                            234,
                            254
                    )
            );

            g2.fillOval(
                    0,
                    0,
                    size,
                    size
            );

            g2.setColor(color);

            g2.fillOval(
                    24,
                    13,
                    17,
                    17
            );

            g2.fillRoundRect(
                    17,
                    34,
                    31,
                    18,
                    12,
                    12
            );

            g2.dispose();
        }
    }

    // =========================================================
    // ADMIN ICON
    // =========================================================
    private static class AdminIcon
            extends JPanel {

        private int size;
        private Color color;

        AdminIcon(
                int size,
                Color color) {

            this.size = size;
            this.color = color;

            setPreferredSize(
                    new Dimension(
                            size,
                            size
                    )
            );

            setOpaque(false);
        }

        @Override
        protected void paintComponent(
                Graphics g) {

            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(
                    new Color(
                            237,
                            233,
                            254
                    )
            );

            g2.fillOval(
                    0,
                    0,
                    size,
                    size
            );

            g2.setColor(color);

            g2.fillRoundRect(
                    19,
                    16,
                    27,
                    35,
                    7,
                    7
            );

            g2.setColor(Color.WHITE);

            g2.fillOval(
                    26,
                    22,
                    13,
                    13
            );

            g2.fillRoundRect(
                    23,
                    37,
                    19,
                    8,
                    5,
                    5
            );

            g2.dispose();
        }
    }

    // =========================================================
    // CANDIDATE ICON
    // =========================================================
    private static class CandidateIcon
            extends JPanel {

        private int size;
        private Color color;
        private String letter;

        CandidateIcon(
                int size,
                Color color,
                String letter) {

            this.size = size;
            this.color = color;
            this.letter = letter;

            setPreferredSize(
                    new Dimension(
                            size,
                            size
                    )
            );

            setOpaque(false);
        }

        @Override
        protected void paintComponent(
                Graphics g) {

            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(
                    new Color(
                            color.getRed(),
                            color.getGreen(),
                            color.getBlue(),
                            35
                    )
            );

            g2.fillOval(
                    0,
                    0,
                    size,
                    size
            );

            g2.setColor(color);

            g2.fillOval(
                    8,
                    8,
                    size - 16,
                    size - 16
            );

            g2.setColor(Color.WHITE);

            g2.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            30
                    )
            );

            FontMetrics fm =
                    g2.getFontMetrics();

            int x =
                    (size
                            - fm.stringWidth(
                            letter
                    )) / 2;

            int y =
                    (size
                            - fm.getHeight()
                    ) / 2
                            + fm.getAscent();

            g2.drawString(
                    letter,
                    x,
                    y
            );

            g2.dispose();
        }
    }

    // =========================================================
    // SET PAGE
    // =========================================================
    private void setPage(
            JPanel page) {

        mainPanel = page;

        setContentPane(mainPanel);

        revalidate();
        repaint();
    }

    // =========================================================
    // MESSAGE
    // =========================================================
    private void showMessage(
            String message,
            String title,
            int type) {

        JOptionPane.showMessageDialog(
                this,
                message,
                title,
                type
        );
    }

    // =========================================================
    // MAIN
    // =========================================================
    public static void main(
            String[] args) {

        OnlineVotingSystem.initialize();

        SwingUtilities.invokeLater(() -> {

            try {

                UIManager.setLookAndFeel(
                        UIManager
                                .getSystemLookAndFeelClassName()
                );

            } catch (Exception ignored) {
            }

            VotingUI ui =
                    new VotingUI();

            ui.setVisible(true);
        });
    }
}