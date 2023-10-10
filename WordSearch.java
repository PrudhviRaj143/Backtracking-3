class Solution {
    boolean[][] visited; // Matrix to keep track of visited cells

    // Main function to check if the word exists on the board
    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || board == null)
            return false;

        int rows = board.length;
        int cols = board[0].length;
        visited = new boolean[rows][cols]; // Initialize the visited matrix

        // Iterate through each cell in the board
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Start the search for the word from this cell if the first character matches
                if (word.charAt(0) == board[i][j] && searchWord(i, j, 0, board, word))
                    return true;
            }
        }
        return false;
    }

    // Recursive function to search for the word on the board
    private boolean searchWord(int i, int j, int index, char[][] board, String word) {
        // If all characters in the word have been found
        if (index == word.length())
            return true;

        // Check if the current cell is out of bounds or visited or does not match the current character of the word
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j] || word.charAt(index) != board[i][j])
            return false;

        visited[i][j] = true; // Mark the cell as visited

        // Check in all four directions for the next character of the word
        if (searchWord(i - 1, j, index + 1, board, word) // up
                || searchWord(i + 1, j, index + 1, board, word) // down
                || searchWord(i, j - 1, index + 1, board, word) // left
                || searchWord(i, j + 1, index + 1, board, word)) // right
            return true;

        visited[i][j] = false; // Mark the cell as not visited (backtrack)

        return false;
    }
}

// Time Complexity: O(m x n x l) where m is the number of rows, n is the number of columns, and l is the length of the word
// Space Complexity: O(l) + O(m x n) where l is the length of the word and m x n is the size of the board
