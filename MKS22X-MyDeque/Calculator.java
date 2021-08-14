public class Calculator {
    public static double eval(String s) {
        String[] tokens = s.split(" ");
        MyDeque<Double> md = new MyDeque<Double>();
        for (String token : tokens) {
            try {
                Double d = Double.parseDouble(token);
                md.addFirst(d);
            } catch (NumberFormatException e) {
                Double arg2 = md.removeFirst();
                Double arg1 = md.removeFirst();
                if(token.equalsIgnoreCase("+")){
                    md.addFirst(arg1+arg2);
                }else if(token.equalsIgnoreCase("-")){
                    md.addFirst(arg1-arg2);
                }else if(token.equalsIgnoreCase("*")){
                    md.addFirst(arg1*arg2);
                }else if(token.equalsIgnoreCase("/")){
                    md.addFirst(arg1/arg2);
                }else if(token.equalsIgnoreCase("%")){
                    md.addFirst(arg1%arg2);
                }
            }
        }
        return md.removeFirst();
    }
}