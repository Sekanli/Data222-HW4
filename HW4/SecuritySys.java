import java.util.Stack;

public class SecuritySys {

    public SecuritySys() {
    }

    /**
     * @param pass1 a password to check
     * @return
     *  // Return 0 for length < 1
        // -1 for other than bracket or letter found
        // -2 for bracket count < 2
        // -3 for letter count < 1
     */
    public int checkpass1(String pass1) {
        
        if (pass1.length() < 8)
            return 0;

        int letterCount = 0;
        boolean skip = false;
        int bracketCount = 0;
        String brackets = "[{()}]";

        for (int i = 0; i < pass1.length(); i++) {
            skip = false;
            if (brackets.indexOf(pass1.charAt(i)) != -1) {
                bracketCount++;
                skip = true;
            }
            if (Character.isLetter(pass1.charAt(i))) {
                letterCount++;
            }
            if (!Character.isLetter(pass1.charAt(i)) && !skip) {
                return -1;
            }
        }
        if (bracketCount < 2)
            return -2;
        if (letterCount < 1)
            return -3;

        return 1;
    }

    /**
     * @param pass2 password to check
     * @return s true if the password is within the boundaries
     */
    public boolean checkpass2(int pass2) {
        if (pass2 < 10 || pass2 > 10000) {
            return false;
        }
        return true;
    }

    /*
     * public static boolean checkIfValidUsername(String username) {
     * if (username.length() == 0) { // base case
     * System.out.println("Username is invalid.It should have at least 1 character"
     * );
     * return false;
     * } else if (username.length() == 1) { // base case
     * return Character.isLetter(username.charAt(0));
     * } else { // recursive case
     * char firstChar = username.charAt(0);
     * return Character.isLetter(firstChar) &&
     * checkIfValidUsername(username.substring(1));
     * }
     * }
     */

    /**
     * @param username 
     * @return s true if no error is encountered
     * returns false if the username length is less than 1 or 
     * the username contains a nonletter and nonbracket character
     */
    public static boolean checkIfValidUsername(String username) {
        if (username.length() == 0) { // base case
            System.out.println("Username is invalid. It should have at least 1 character");
            return false;
        } else if (username.length() == 1) { // base case
            if (Character.isLetter(username.charAt(0))) {
                return true;
            } else {
                System.out.println(
                        "Username is invalid. It contains a non-letter character: " + username.charAt(0) );
                return false;
            }
        } else { // recursive case
            char firstChar = username.charAt(0);
            if (Character.isLetter(firstChar)) {
                return checkIfValidUsername(username.substring(1));
            } else {
                System.out.println("Username is invalid. It contains a non-letter character: " + firstChar);
                return false;
            }
        }
    }

    /**
     * checks if the password contains at least 1 char from the username
     * @param username
     * @param password1
     * @return true if it contains,false if not
     */
    public boolean containsUserNameSpirit(String username, String password1) {
        Stack<Character> passwordStack = new Stack<>();

        for (int i = 0; i < password1.length(); i++) {
            passwordStack.push(password1.charAt(i));
        }

        while (!passwordStack.isEmpty()) {
            char currentChar = passwordStack.pop();
            if (username.indexOf(currentChar) != -1) {
                return true;
            }
        }

        return false;
    }

    /**a helper function for isBalancedPass to check if the given 2 brackets are of the same type
     * @param ch1
     * @param ch2
     * @return
     */
    public boolean isMatch(char ch1, char ch2) {
        if ((ch1 == '(' && ch2 == ')') || (ch1 == '[' && ch2 == ']') || (ch1 == '{' && ch2 == '}')) {
            return true;
        }

        return false;
    }

    /**
     * Function to check if the given password parantheses are balanced
     * @param password1
     * @return true if no error encountered
     * false if a full match isnt detected
     */
    public boolean isBalancedPassword(String password1) {
        Stack<Character> parantheses = new Stack<>();
        String open = "{[(";
        String closed = "}])";
        boolean balanced = true;
        int index = 0;

        while (balanced && index < password1.length()) {
            char nextCh = password1.charAt(index);
            if (open.indexOf(nextCh) != -1) {
                parantheses.push(nextCh);
            } else if (closed.indexOf(nextCh) != -1) {
                if (parantheses.empty()) {
                    balanced = false;
                } else {
                    char topCh = parantheses.pop();
                    balanced = isMatch(topCh, nextCh);
                }
            }
            index++;
        }

        return balanced && parantheses.empty();
    }

    /// 5th part
    /** function to check if you can achieve the password by the summation elements of denominations
     * @param password2
     * @param denominations int array to hold multiple values
     * @return
     */
    public boolean isExactDivision(int password2, int[] denominations) {
        int size = denominations.length;
        int[] coefficients = new int[size];

        return calc(password2, denominations, 0, coefficients);
    }

    /**Helper recursive function for isExactdiv
     * 
     * @param password2 
     * @param denominations 
     * @param sum
     * @param coefficients an int array to hold how many of which of the denominations coefs is used
     * @return
     * 
     * The function that checks the every combination of denomination elements and returns the sum
     * If at least 1 solution is found, possible is made true and the function returns true
     * If none solution is found,returns false
     */
    public boolean calc(int password2, int[] denominations, int sum, int[] coefficients) {
        boolean possible = false;

        if (sum > password2) {
            return false;
        }
        if (sum == password2) {
            return true;
        }
        // increment the coef[i] every loop, if the loop isnt broken by
        // possible break,decrement it so it wont change
        for (int i = 0; i < denominations.length; i++) {
            coefficients[i]++;
            possible = calc(password2, denominations, sum + denominations[i], coefficients);
            if (possible)
                break;
            coefficients[i]--;
        }

        return possible;
    }

    // 4th part
    /**
     * @param password1
     * @return
     * Function first deletes the brackets from the password then makes a recursive call 
     * to the helper recpalcheck function to check the occurrences
     * If there are more than 1 occurrences of characters with odd number counts,palindromes are not achievable
     * If not returns true
     */
    public boolean isPalindromePossible(String password1) {
        String brackets = "(){}[]";
        int oddCount = 0;
        int[] charCount = new int[52];
        int nonBracketChars = 0;
        for (int i = 0; i < password1.length(); i++) {
            if (brackets.indexOf(password1.charAt(i)) == -1)
                nonBracketChars++;
        }

        char[] seq = new char[nonBracketChars];
        int index = 0;
        for (int i = 0; i < password1.length(); i++) {
            if (brackets.indexOf(password1.charAt(i)) == -1) {
                seq[index++] = password1.charAt(i);
            }
        }

        recPalCheck(seq, 0, charCount);

        for (int count : charCount) {
            if (count % 2 == 1)
                oddCount++;
        }

        return oddCount <= 1;
    }

    /** A helper recursive function to check number of occurrences in the password
     * @param seq the password purified from brackets
     * @param index track of the current index, need for recursive calls
     * @param charCount a 52 sized array to count the occurrences of each letter
     */
    private void recPalCheck(char[] seq, int index, int[] charCount) {
        if (index >= seq.length) {
            return;
        }

        char ch = seq[index];
        if (ch >= 'A' && ch <= 'Z') {
            charCount[ch - 'A']++;
        }
        if (ch >= 'a' && ch <= 'z') {
            charCount[ch - 'a' + 26]++;
        }

        recPalCheck(seq, index + 1, charCount);
    }

    ///////////////////////

    /** The System check function to check the username , password1 and password2 of the
     * museumofficer
     * If all checks are done and no error is found,return true
     * else give an error message and return false
     * @param off   the museum officer object
     * @return
     */
    public boolean SystemCheck(MuseumOfficer off) {

        int[] denoms = { 4, 17, 29 };
        boolean error = false;
        System.out.println(
                "For " + off.getUsername() + " with passwords " + off.getPassword1() + " and " + off.getPassword2());

        if (checkpass1(off.getPassword1()) <= 0) {
            error = true;
            int msg = checkpass1(off.getPassword1());
            if (msg == 0) {
                System.out.println("Password1 is invalid.It should have at least 8 characters.");
            }
            if (msg == -1) {
                System.out.println("Password1 contains a character other than bracket or letter");
            }
            if (msg == -2) {
                System.out.println("Password1 is invalid.It should have at least 2 brackets");
            }
            if (msg == -3) {
                System.out.println("Password1 is invalid. It should have letters too.");
            }
        }

        if (!checkpass2(off.getPassword2())) {
            System.out.println("Invalid password2 boundries.It should be between 10 and 10000");
            error = true;

        }
        // I printed the error message on the check function instead of here to avoid
        // complex operations
        if (!checkIfValidUsername(off.getUsername())) {
            error = true;
        }

        if (!containsUserNameSpirit(off.getUsername(), off.getPassword1())) {
            System.out.println("The password1 is invalid.It should have at least 1 character from the username.");
            error = true;

        }

        if (!isBalancedPassword(off.getPassword1())) {
            System.out.println("The password1 is invalid.It should be balanced");
            error = true;

        }

        if (!isPalindromePossible(off.getPassword1())) {
            System.out.println("The password1 is invalid.It should be possible to obtain a palindrome.");
            error = true;

        }

        if (!isExactDivision(off.getPassword2(), denoms)) {
            System.out.println("The password2 is invalid. It is not compatible with the denominations.");
            error = true;
        }
        if (!error) {
            System.out.println("The username and passwords are valid. The door is opening, please wait..");
        }
        System.out.println();
        return !error;

    }

}
