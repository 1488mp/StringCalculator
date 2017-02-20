package calc;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.util.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FXMLController implements Initializable {
    double x, y;    
    public static Stage stage;  
    
    @FXML
    private TextField textField;
    
    @FXML
    private void clickC() {
        textField.setText("0");
    }    
    @FXML
    private void clickPoint() {
        if (!textField.getText().endsWith(".")) {
            textField.setText(textField.getText() + ".");             
        }
    }    
    @FXML
    private void click0() {
        if (!textField.getText().equals("0")) {
            textField.setText(textField.getText() + "0");             
        }
    }
    @FXML
    private void click1() {
        if (textField.getText().equals("0")) {
            textField.setText("1");
        } else {
            textField.setText(textField.getText() + "1");  
        }
    }
    @FXML
    private void click2() {
        if (textField.getText().equals("0")) {
            textField.setText("2");
        } else {
            textField.setText(textField.getText() + "2");  
        }
    }
    @FXML
    private void click3() {
        if (textField.getText().equals("0")) {
            textField.setText("3");
        } else {
            textField.setText(textField.getText() + "3");  
        }    }
    @FXML
    private void click4() {
        if (textField.getText().equals("0")) {
            textField.setText("4");
        } else {
            textField.setText(textField.getText() + "4");  
        }
    }
    @FXML
    private void click5() {
        if (textField.getText().equals("0")) {
            textField.setText("5");
        } else {
            textField.setText(textField.getText() + "5");  
        }        
    }
    @FXML
    private void click6() {
        if (textField.getText().equals("0")) {
            textField.setText("6");
        } else {
            textField.setText(textField.getText() + "6");  
        }
    }
    @FXML
    private void click7() {
        if (textField.getText().equals("0")) {
            textField.setText("7");
        } else {
            textField.setText(textField.getText() + "7");  
        }
    }
    @FXML
    private void click8() {
        if (textField.getText().equals("0")) {
            textField.setText("8");
        } else {
            textField.setText(textField.getText() + "8");  
        }
    }
    @FXML
    private void click9() {
        if (textField.getText().equals("0")) {
            textField.setText("9");
        } else {
            textField.setText(textField.getText() + "9");  
        }
    }
    @FXML
    private void clickPlus() {
        textField.setText(textField.getText() + "+");        
    }
    @FXML
    private void clickMinus() {
        textField.setText(textField.getText() + "-");
    }
    @FXML
    private void clickPow() {
        textField.setText(textField.getText() + "*");
    }
    @FXML
    private void clickDivide() {
        textField.setText(textField.getText() + "/");
    }
    @FXML
    private void clickOpen() {
        if (textField.getText().equals("0")) {
            textField.setText("(");
        } else {
            textField.setText(textField.getText() + "(");  
        }
    }
    @FXML
    private void clickClose() {
        textField.setText(textField.getText() + ")");
    }
        
    public Double calc(List<String> postfix) {
        Deque<Double> stack = new ArrayDeque<>();
        for (String x : postfix) {
            if (x.equals("sqrt")) {
                stack.push(Math.sqrt(stack.pop()));
            } else if (x.equals("cube")) {
                Double tmp = stack.pop();
                stack.push(tmp * tmp * tmp);
            } else if (x.equals("pow10")) {
                stack.push(Math.pow(10, stack.pop()));
            } else if (x.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (x.equals("-")) {
                Double b = stack.pop(), a = stack.pop();
                stack.push(a - b);
            } else if (x.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (x.equals("/")) {
                Double b = stack.pop(), a = stack.pop();
                stack.push(a / b);
            } else if (x.equals("u-")) {
                stack.push(-stack.pop());
            } else {
                stack.push(Double.valueOf(x));
            }
        }
        return stack.pop();
    }
    
    private final String operators = "+-*/";
    private final String delimiters = "() " + operators;
    public boolean flag = true;

    private boolean isDelimiter(String token) {
        if (token.length() != 1) {
            return false;
        }
        for (int i = 0; i < delimiters.length(); i++) {
            if (token.charAt(0) == delimiters.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    private boolean isOperator(String token) {
        if (token.equals("u-")) {
            return true;
        }
        for (int i = 0; i < operators.length(); i++) {
            if (token.charAt(0) == operators.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    private boolean isFunction(String token) {
        return token.equals("sqrt") || token.equals("cube") || token.equals("pow10");
    }

    private int priority(String token) {
        if (token.equals("(")) {
            return 1;
        }
        if (token.equals("+") || token.equals("-")) {
            return 2;
        }
        if (token.equals("*") || token.equals("/")) {
            return 3;
        }
        return 4;
    }

    public List<String> parse(String infix) {
        List<String> postfix = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();
        StringTokenizer tokenizer = new StringTokenizer(infix, delimiters, true);
        String prev = "";
        String curr = "";
        while (tokenizer.hasMoreTokens()) {
            curr = tokenizer.nextToken();
            if (!tokenizer.hasMoreTokens() && isOperator(curr)) {
                textField.setText("Invalid exp.");
                flag = false;
                return postfix;
            }
            if (curr.equals(" ")) {
                continue;
            }
            if (isFunction(curr)) {
                stack.push(curr);
            } else if (isDelimiter(curr)) {
                if (curr.equals("(")) {
                    stack.push(curr);
                } else if (curr.equals(")")) {
                    while (!stack.peek().equals("(")) {
                        postfix.add(stack.pop());
                        if (stack.isEmpty()) {
                            textField.setText("Invalid exp. skobki");
                            flag = false;
                            return postfix;
                        }
                    }
                    stack.pop();
                    if (!stack.isEmpty() && isFunction(stack.peek())) {
                        postfix.add(stack.pop());
                    }
                } else {
                    if (curr.equals("-") && (prev.equals("") || (isDelimiter(prev) && !prev.equals(")")))) {
                        // унарный минус
                        curr = "u-";
                    } else {
                        while (!stack.isEmpty() && (priority(curr) <= priority(stack.peek()))) {
                            postfix.add(stack.pop());
                        }

                    }
                    stack.push(curr);
                }

            } else {
                postfix.add(curr);
            }
            prev = curr;
        }

        while (!stack.isEmpty()) {
            if (isOperator(stack.peek())) {
                postfix.add(stack.pop());
            } else {
                textField.setText("Invalid exp. skobki");
                flag = false;
                return postfix;
            }
        }
        return postfix;
    }

    @FXML
    private void clickFinally() {
        String s = textField.getText();
        List<String> expression = parse(s);
	if (flag) {
            expression.stream().forEach((String) -> {
                if(String.valueOf(calc(expression)).endsWith(".0")) {
                    textField.setText(String.valueOf(calc(expression))
                            .substring(0, String.valueOf(calc(expression))
                                    .length() - 2));
                } else {
                    textField.setText(String.valueOf(calc(expression)));
                }
            });
        }
    } 
    
    @FXML
    private void clickHide() {
        stage.setIconified(true);
    }
    @FXML
    private void clickExit() {
        stage.close();        
    }
    
    @FXML
    private void mPressed(MouseEvent me) {
        x = stage.getX() - me.getScreenX();
        y = stage.getY() - me.getScreenY();
    }
    @FXML
    private void mDragged(MouseEvent me) {
        stage.setX(me.getScreenX() + x);
        stage.setY(me.getScreenY() + y);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }        
}
