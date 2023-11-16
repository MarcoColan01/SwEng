package it.unimi.di.sweng.lab03;

import java.util.*;

public class ForthInterpreter implements Interpreter {
    //private String program;
    private final String top = "<- Top";
    private final Deque<String> stack = new LinkedList<>();
    private final Map<String, Runnable> operations = new HashMap<>();

    public ForthInterpreter(){
        operations.put("+", new Runnable(){
            public void run(){
                sum();
            }
        });
        operations.put("*", new Runnable(){
            public void run(){
                prod();
            }
        });

        operations.put("-", new Runnable(){
            public void run(){
                sub();
            }
        });

        operations.put("/", new Runnable(){
            public void run(){
                div();
            }
        });

        operations.put("dup", new Runnable(){
            public void run(){
                dup();
            }
        });

        operations.put("swap", new Runnable(){
            public void run(){
                swap();
            }
        });

        operations.put("drop", new Runnable(){
            public void run(){
                drop();
            }
        });
    }

    private void sum() {
        int op1 = Integer.parseInt(stack.pop());
        if(stack.isEmpty()) throw new IllegalArgumentException("Stack Underflow");
        int op2 = Integer.parseInt(stack.pop());
        stack.push(String.valueOf(op1+op2));
    }

    private void div() {
        int op1 = Integer.parseInt(stack.pop());
        if(stack.isEmpty()) throw new IllegalArgumentException("Stack Underflow");
        int op2 = Integer.parseInt(stack.pop());
        stack.push(String.valueOf(op2/op1));
    }

    private void sub() {
        int op1 = Integer.parseInt(stack.pop());
        if(stack.isEmpty()) throw new IllegalArgumentException("Stack Underflow");
        int op2 = Integer.parseInt(stack.pop());
        stack.push(String.valueOf(op2-op1));
    }

    private void prod() {
        int op1 = Integer.parseInt(stack.pop());
        if(stack.isEmpty()) throw new IllegalArgumentException("Stack Underflow");
        int op2 = Integer.parseInt(stack.pop());
        stack.push(String.valueOf(op1*op2));
    }

    private void dup(){
        if(stack.isEmpty()) throw new NoSuchElementException();
        stack.push(stack.getFirst());
    }

    private void swap(){
        String app1 = stack.pop();
        if(stack.isEmpty()) throw new NoSuchElementException();
        String app2 = stack.pop();
        stack.push(app1);
        stack.push(app2);
    }

    private void drop(){
        if(stack.isEmpty()) throw new NoSuchElementException();
        stack.removeFirst();
    }

    @Override
    public void input(String program) {
        String [] app = program.replaceAll("\\s+", " ").
                replaceAll("[^a-zA-Z0-9 +\\-*/]", "").split(" ");
        for(String s: app){
            if(s.equals(":")) addWord(app);
            if(s.length() == 2 && (s.charAt(1) == '+' || s.charAt(0) == '+')) throw new
                    IllegalArgumentException("Token error '" + s + "'");
            if(operations.containsKey(s)){
                operations.get(s).run();
            }else{
                stack.push(s);
            }
        }
    }

    private void addWord(String[] app) {
        operations.put(app[1], new Runnable(){
            public void run(){

            }
        });
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        if(stack.isEmpty()) return top;
        while(!stack.isEmpty()){
            sb.append(stack.getLast()).append(" ");
            stack.removeLast();
        }
        return sb.append(top).toString();
    }
}
