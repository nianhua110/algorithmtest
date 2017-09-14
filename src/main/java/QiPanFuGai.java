/*
 * Copyright (c) 2017, CipherGateway and/or its affiliates. All rights  reserved.
 *
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Random;

/**
 * 题目要求在棋盘覆盖问题中,要用下图—图(2)所示的4种不同形态的L型骨牌覆盖一个给定的特殊棋盘上除特殊方格以外的所有方格,且任何2个L型骨牌不得重叠覆盖.
 *
 * @author kyle
 */
public class QiPanFuGai extends Application {
  static String colors = "#000000\n" +
      "#696969\n" +
      "#808080\n" +
      "#A9A9A9\n" +
      "#C0C0C0\n" +
      "#D3D3D3\n" +
      "#DCDCDC\n" +
      "#F5F5F5\n" +
      "#FFFFFF\n" +
      "#FFFAFA\n" +
      "#E6C3C3\n" +
      "#BC8F8F\n" +
      "#F08080\n" +
      "#CD5C5C\n" +
      "#A52A2A\n" +
      "#B22222\n" +
      "#800000\n" +
      "#8B0000\n" +
      "#E60000\n" +
      "#FF0000\n" +
      "#FF4D40\n" +
      "#FFE4E1\n" +
      "#FA8072\n" +
      "#FF2400\n" +
      "#FF6347\n" +
      "#E9967A\n" +
      "#FF7F50\n" +
      "#FF4500\n" +
      "#FFA07A\n" +
      "#FF4D00\n" +
      "#A0522D\n" +
      "#FF8033\n" +
      "#A16B47\n" +
      "#E69966\n" +
      "#4D1F00\n" +
      "#FFF5EE\n" +
      "#8B4513\n" +
      "#D2691E\n" +
      "#CC5500\n" +
      "#FF7300\n" +
      "#FFDAB9\n" +
      "#F4A460\n" +
      "#B87333\n" +
      "#FAF0E6\n" +
      "#FFB366\n" +
      "#CD853F\n" +
      "#704214\n" +
      "#CC7722\n" +
      "#FFE4C4\n" +
      "#F28500\n" +
      "#FF8C00\n" +
      "#FAEBD7\n" +
      "#D2B48C\n" +
      "#DEB887\n" +
      "#FFEBCD\n" +
      "#FFDEAD\n" +
      "#FF9900\n" +
      "#FFEFD5\n" +
      "#CCB38C\n" +
      "#996B1F\n" +
      "#FFE4B5\n" +
      "#FDF5E6\n" +
      "#F5DEB3\n" +
      "#FFE5B4\n" +
      "#FFA500\n" +
      "#FFFAF0\n" +
      "#DAA520\n" +
      "#B8860B\n" +
      "#4D3900\n" +
      "#E6C35C\n" +
      "#FFBF00\n" +
      "#FFF8DC\n" +
      "#E6B800\n" +
      "#FFD700\n" +
      "#FFFACD\n" +
      "#F0E68C\n" +
      "#EEE8AA\n" +
      "#BDB76B\n" +
      "#E6D933\n" +
      "#FFFDD0\n" +
      "#FFFFF0\n" +
      "#F5F5DC\n" +
      "#FFFFE0\n" +
      "#FAFAD2\n" +
      "#FFFF99\n" +
      "#CCCC4D\n" +
      "#FFFF4D\n" +
      "#808000\n" +
      "#FFFF00\n" +
      "#FFFF00\n" +
      "#697723\n" +
      "#CCFF00\n" +
      "#6B8E23\n" +
      "#9ACD32\n" +
      "#556B2F\n" +
      "#8CE600\n" +
      "#ADFF2F\n" +
      "#99E64D\n" +
      "#7CFC00\n" +
      "#7FFF00\n" +
      "#73B839\n" +
      "#99FF4D\n" +
      "#66FF00\n" +
      "#66FF59\n" +
      "#F0FFF0\n" +
      "#8FBC8F\n" +
      "#90EE90\n" +
      "#98FB98\n" +
      "#36BF36\n" +
      "#228B22\n" +
      "#32CD32\n" +
      "#006400\n" +
      "#008000\n" +
      "#00FF00\n" +
      "#22C32E\n" +
      "#16982B\n" +
      "#73E68C\n" +
      "#50C878\n" +
      "#4DE680\n" +
      "#127436\n" +
      "#A6FFCC\n" +
      "#2E8B57\n" +
      "#3CB371\n" +
      "#F5FFFA\n" +
      "#00FF80\n" +
      "#00A15C\n" +
      "#00FA9A\n" +
      "#66CDAA\n" +
      "#7FFFD4\n" +
      "#0DBF8C\n" +
      "#66FFE6\n" +
      "#33E6CC\n" +
      "#30D5C8\n" +
      "#20B2AA\n" +
      "#48D1CC\n" +
      "#E0FFFF\n" +
      "#E0FFFF\n" +
      "#AFEEEE\n" +
      "#2F4F4F\n" +
      "#008080\n" +
      "#008B8B\n" +
      "#00FFFF\n" +
      "#00FFFF\n" +
      "#00CED1\n" +
      "#5F9EA0\n" +
      "#00808C\n" +
      "#B0E0E6\n" +
      "#006374\n" +
      "#ADD8E6\n" +
      "#7AB8CC\n" +
      "#4798B3\n" +
      "#00BFFF\n" +
      "#87CEEB\n" +
      "#87CEFA\n" +
      "#00477D\n" +
      "#4682B4\n" +
      "#F0F8FF\n" +
      "#708090\n" +
      "#778899\n" +
      "#1E90FF\n" +
      "#004D99\n" +
      "#007FFF\n" +
      "#5686BF\n" +
      "#B0C4DE\n" +
      "#0047AB\n" +
      "#5E86C1\n" +
      "#6495ED\n" +
      "#4D80E6\n" +
      "#003399\n" +
      "#082567\n" +
      "#002FA7\n" +
      "#2A52BE\n" +
      "#4169E1\n" +
      "#24367D\n" +
      "#0033FF\n" +
      "#0D33FF\n" +
      "#F8F8FF\n" +
      "#E6E6FA\n" +
      "#CCCCFF\n" +
      "#191970\n" +
      "#000080\n" +
      "#00008B\n" +
      "#0000CD\n" +
      "#0000FF\n" +
      "#5C50E6\n" +
      "#483D8B\n" +
      "#6A5ACD\n" +
      "#7B68EE\n" +
      "#6640FF\n" +
      "#B399FF\n" +
      "#9370DB\n" +
      "#6633CC\n" +
      "#8674A1\n" +
      "#5000B8\n" +
      "#B8A1CF\n" +
      "#8A2BE2\n" +
      "#8B00FF\n" +
      "#4B0080\n" +
      "#9932CC\n" +
      "#9400D3\n" +
      "#7400A1\n" +
      "#D94DFF\n" +
      "#E680FF\n" +
      "#BA55D3\n" +
      "#E6CFE6\n" +
      "#D8BFD8\n" +
      "#CCA3CC\n" +
      "#DDA0DD\n" +
      "#EE82EE\n" +
      "#800080\n" +
      "#8B008B\n" +
      "#FF00FF\n" +
      "#FF00FF\n" +
      "#DA70D6\n" +
      "#FFB3E6\n" +
      "#B85798\n" +
      "#FF66CC\n" +
      "#C71585\n" +
      "#FF0DA6\n" +
      "#CC0080\n" +
      "#E63995\n" +
      "#FF1493\n" +
      "#E68AB8\n" +
      "#FF80BF\n" +
      "#FF69B4\n" +
      "#470024\n" +
      "#FF73B3\n" +
      "#E6005C\n" +
      "#FFD9E6\n" +
      "#990036\n" +
      "#FFF0F5\n" +
      "#DB7093\n" +
      "#DE3163\n" +
      "#FF8099\n" +
      "#DC143C\n" +
      "#FFC0CB\n" +
      "#FFB6C1\n" +
      "#FFB3BF\n" +
      "#E32636\n";
  public static int title = 0;

  public static void chessBorad(int[][] board, int bx, int by, int sx, int sy, int size) {
    if (size == 1) {
      return;
    }
    int t = title++;
    int s = size / 2;
    if (sx < bx + s && sy < by + s) {
      chessBorad(board, bx, by, sx, sy, s);
    } else {
      board[bx + s - 1][by + s - 1] = t;
      chessBorad(board, bx, by, bx + s - 1, by + s - 1, s);
    }

    if (sx < bx + 2 * s && sx >= bx + s && sy < by + s) {
      chessBorad(board, bx + s, by, sx, sy, s);
    } else {
      board[bx + s][by + s - 1] = t;
      chessBorad(board, bx + s, by, bx + s, by + s - 1, s);
    }

    if (sx < bx + s && sy < by + 2 * s && sy >= by + s) {
      chessBorad(board, bx, by + s, sx, sy, s);
    } else {
      board[bx + s - 1][by + s] = t;

      chessBorad(board, bx, by + s, bx + s - 1, by + s, s);
    }

    if (sx < bx + 2 * s && sx >= bx + s && sy < by + 2 * s && sy >= by + s) {
      chessBorad(board, bx + s, by + s, sx, sy, s);
    } else {
      board[bx + s][by + s] = t;
      chessBorad(board, bx + s, by + s, bx + s - 1, by + s - 1, s);
    }
  }

  public static void prinChess(int[][] board) {
    Arrays.stream(board)
        .map(Arrays::toString)
        .forEachOrdered(System.out::println);
  }

  public static void showChess(int[][] board, Stage stage) {
    String[] colorTable = colors.split("\n");
    int[] chooseTable = new Random().ints(0, colorTable.length)
        .distinct()
        .limit(title+1)
        .toArray();
    //Creating a Grid Pane
    GridPane gridPane = new GridPane();

    //Setting size for the pane
    gridPane.setMinSize(400, 200);

    //Setting the padding
    gridPane.setPadding(new Insets(10, 10, 10, 10));

    //Setting the vertical and horizontal gaps between the columns
    //gridPane.setVgap(5);
   // gridPane.setHgap(5);

    //Setting the Grid alignment
    gridPane.setAlignment(Pos.CENTER);

    Text t = new Text("3");
    t.setStyle("-fx-background-color: rebeccapurple");
    javafx.scene.control.TextArea textArea = new TextArea("er");
    textArea.setStyle("-fx-background-color: blue");
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        int c = board[i][j] ;
        String finalColor = c >=0 ? colorTable[chooseTable[c]] :colorTable[chooseTable[title]];
        Label label = new Label(String.valueOf(board[i][j]));
        String style = "-fx-background-color: " +finalColor + ";" +
            "-fx-border-style: solid;" +
            "-fx-pref-width: 50px;" +
            "-fx-pref-height:50px";
        label.setStyle(style);
        gridPane.add(label, i, j);
      }
    }

    //Creating a scene object
    Scene scene = new Scene(gridPane);

    //Setting title to the Stage
    stage.setTitle("Grid Pane Example");

    //Adding scene to the stage
    stage.setScene(scene);

    //Displaying the contents of the stage
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    int[][] a = new int[8][8];
    int sx = 4;
    int sy = 3;
    a[sx][sy] = -1;
    chessBorad(a, 0, 0, sx, sy, a.length);
    prinChess(a);
    showChess(a, primaryStage);
  }
}
