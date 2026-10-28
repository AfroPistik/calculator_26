/*orizetai ti dieuthinsi tou arxeiou mesa sto project*/
package com.team.calculator.controller;

/*dimosia klasi gia xrisi apo alla meri tis efarmogis*/
public class InputValidator 
{

    /*methodos pou epistrefei true or false*/
    public boolean isValid(String currentExpression, String token) 
    {
        /*new character is null so no need to check*/
        if (token == null || token.isEmpty()) 
        {
            return false;
        }
        
        /*an i nea ekfrasi einai null tote i simvoloseira ginetai adeia*/
        if (currentExpression == null) 
        {
            currentExpression = "";
        }
        
        
        /*pairnei to xaraktira apo to token kai to apothikeuei sti metabliti t*/
        char t = token.charAt(0);
        /*apothikeuei ton teleutaio xaraktira tis ekfrasis, an einai adeia i ekfrasi bazei 0*/
        char lastChar = currentExpression.isEmpty() ? '\0' : currentExpression.charAt(currentExpression.length() - 1);

        /*an h ekfrasi ksekina me * or / einai lathos kai epistrefei false */
        if (currentExpression.isEmpty() && (t == '*' || t == '/')) 
        {
            return false;
        }

        /*an oi dio teleutaioi xaraktires einai telestes*/
        if (isOperator(t) && isOperator(lastChar)) 
        {
            /*epistrefei false ektos an einai 2 diadoxikoi xaraktires opos + -5 or + (-5)*/
            if (!(t == '-' && (lastChar == '+' || lastChar == '*' || lastChar == '/' || lastChar == '('))) 
            {
                return false;
            }
        }

        /* an o xaraktiras einai . or ,*/
        if (t == '.' || t == ',') 
        {
            int lastSep = Math.max(
                Math.max(currentExpression.lastIndexOf('+'), currentExpression.lastIndexOf('-')),
                Math.max(
                    Math.max(currentExpression.lastIndexOf('*'), currentExpression.lastIndexOf('/')),
                    currentExpression.lastIndexOf('(')
                )
            );
            String currentNumber = lastSep == -1 ? currentExpression : currentExpression.substring(lastSep + 1);
            /* an o arithmos exei dipli . or , px 3.4.5 tote na bgazei lathos*/
            if (currentNumber.contains(".") || currentNumber.contains(",")) {
                return false;
            }
        }

        /* an o xaraktiras einai kleisti parenthesi*/
        if (t == ')') 
        {
            /*metraei poses anoixtes kai kleistes parentheseis iparxoun stin ekfrasi*/
            long openCount = currentExpression.chars().filter(ch -> ch == '(').count();
            long closeCount = currentExpression.chars().filter(ch -> ch == ')').count();
            /*an oi kleistes einai hdh oses einai oi anoixtes h perissoteres tote bgazei lathos*/
            if (closeCount >= openCount) 
            {
                return false;
            }
        }

        return true;
    }

    /*enas extra elegxos gia an einai mathimatikos telestis*/
    private boolean isOperator(char c) 
    {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
}
