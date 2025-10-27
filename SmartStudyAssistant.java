// SmartStudyAssistant.java
// Simple AI-like Smart Study Assistant using JavaFX (Beginner Level)

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.Arrays;
import java.util.LinkedHashSet;

public class SmartStudyAssistant extends Application {

    private TextArea inputArea;
    private TextArea outputArea;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("📚 Smart Study Assistant");

        // Input area
        Label inputLabel = new Label("Enter your study text or notes:");
        inputArea = new TextArea();
        inputArea.setPromptText("Type or paste your study content here...");
        inputArea.setWrapText(true);

        // Buttons
        Button summarizeBtn = new Button("✨ Generate Summary");
        Button clearBtn = new Button("🧹 Clear");

        summarizeBtn.setOnAction(e -> {
            String input = inputArea.getText();
            String summary = summarizeText(input);
            outputArea.setText(summary);
        });

        clearBtn.setOnAction(e -> {
            inputArea.clear();
            outputArea.clear();
        });

        // Output area
        Label outputLabel = new Label("AI Summary Output:");
        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setWrapText(true);

        // Layout setup
        HBox buttonBox = new HBox(10, summarizeBtn, clearBtn);
        buttonBox.setPadding(new Insets(10, 0, 10, 0));

        VBox layout = new VBox(10, inputLabel, inputArea, buttonBox, outputLabel, outputArea);
        layout.setPadding(new Insets(15));
        layout.setStyle("-fx-background-color: #f9f9fb; -fx-font-family: 'Segoe UI';");

        Scene scene = new Scene(layout, 700, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // --- Improved AI-like summarizer (simple & clean) ---
    private String summarizeText(String text) {
        if (text.trim().isEmpty()) {
            return "⚠️ Please enter some text first!";
        }

        // Split text into sentences
        String[] sentences = text.split("(?<=[.!?])\\s+");
        if (sentences.length <= 2) {
            return "📝 Your text is already concise!";
        }

        // Sort sentences by length (approx importance)
        Arrays.sort(sentences, (a, b) -> Integer.compare(b.length(), a.length()));

        // Pick only top 3 unique sentences
        java.util.Set<String> unique = new LinkedHashSet<>();
        for (String s : sentences) {
            if (unique.size() >= 3) break;
            unique.add(s.trim());
        }

        // Build final summary
        StringBuilder summary = new StringBuilder("📚 Summary:\n\n");
        for (String s : unique) {
            summary.append("• ").append(s).append("\n");
        }

        return summary.toString();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
