package fr.maximeNarbaud.chess.Aspect;

import fr.maximeNarbaud.chess.Board;
import fr.maximeNarbaud.chess.agent.Move;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public aspect LogAspect {

    private static File filePath;

    // Pointcut+Advice to generate file path
    after(): execution(void *.setupChessBoard()) {
        initFileName();
    }

    // Pointcut+Advice to write in file when a player make a valid move
    after(Board board, Move mv): execution(void *.movePiece(Move)) && target(board) && args(mv) {
        writeInFile(board.toString() + mv.toString() + "\n\n");
    }

    private void initFileName() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
        String directoryPath = "output/";
        String fileName = "gameLog_" + sdf.format(new Date()) + ".log";

        File directory = new File(directoryPath);

        // Check if directory exist
        if (! directory.exists()) {
            // If you require it to make the entire directory path including parents, use directory.mkdirs(); here instead
            directory.mkdir();
        }

        filePath = new File(directoryPath + fileName);

        try {
            // createNewFile() return true if the file was successfully created
            // Sets filePath to null if the file already exists so as not overwrite an existing game log
            if (!filePath.createNewFile()) { filePath = null; }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeInFile(String text) {
        if (filePath != null) {
            try {
                FileWriter fileWriter = new FileWriter(filePath, true);
                fileWriter.write(text);
                fileWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
