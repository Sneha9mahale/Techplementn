package com.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class QuizGenerator {
	private static Map<String, Quiz> quizzes = new HashMap<>(); 

	public static void main(String[] args) {
			Scanner sc=new Scanner(System.in);
			while(true)
			{
				System.out.println("Enter a Command You want to perform : \n (Create , Take , View , List , Exit ) ");
				String command = sc.nextLine();
				if(command.equals("Create"))
				{
					createQuiz(sc);
				}
				else if(command.equals("Take"))
				{
					takeQuiz(sc);
				}
				else if(command.equals("View"))
				{
					viewQuiz(sc);
				}
				else if(command.equals("List"))
				{
					listQuiz();
				}
				else if(command.equals("Exit"))
				{
					break;
				}
				else
				{
					System.out.println("Invalid Command");
				}
			}
	}
	private static void createQuiz(Scanner sc)
	{
		System.out.println("Enter The Subject Of Quiz : ");
		String quizSub = sc.nextLine();
		Quiz quiz = new Quiz(quizSub);
		System.out.println("Enter the Number Of Questions :  ");
		//Scanner scanner;
		//int numQuestions = sc.nextInt();
		 int numQuestions = Integer.parseInt(sc.nextLine());
		for(int i = 0;i < numQuestions;i++)
		{
			System.out.println("Enter the questions : ");
			String question = sc.nextLine();
			System.out.println("Enter the number of choices : ");
			int numChoices = Integer.parseInt(sc.nextLine());
			List<String> choices = new ArrayList<>();
			for(int j=0; j < numChoices ; j++)
			{
				System.out.println("Enter Choice "+ (j+1) + ":");
				String choice = sc.nextLine();
				choices.add(choice);
			}
			System.out.println(" e=Enter the Index Of The Correct Answer : ");
			int correctChoice = Integer.parseInt(sc.nextLine()) - 1;
			quiz.addQuestion(new Question(question, choices, correctChoice));
		}
		quizzes.put(quizSub, quiz);
		System.out.println("Quiz created. ");
	}
	private static void takeQuiz(Scanner sc)
	{
		System.out.println("Enter the name of the Quiz : ");
		String quizSub = sc.nextLine();
		Quiz quiz =  quizzes.get(quizSub);
		if (quiz == null)
		{
			System.out.println("Quiz not found.");
			return;
		}
		int score = 0;
		for(int i = 0;i < quiz.getNumQuestions(); i++)
		{
			Question question = quiz.getQuestion(i);
			System.out.println("Question " + (i+1) +" : "+ question.getQuestion());
			List<String> choices = question.getChoices();
			for(int j = 0;j < choices.size(); j++)
			{
				System.out.println((j+1) +" : "+ choices.get(j));
			}
			System.out.println("Enter your Answer ");
			int userAnswer = Integer.parseInt(sc.nextLine()) - 1;
			
			if(userAnswer == question.getCorrectChoice())
			{
				System.out.println("Correct");
				score++;
			}
			else
			{
				System.out.println("Incoorect. The answer is "+ (question.getCorrectChoice()+1)+ " . ");
				
			}
		}
		System.out.println("Your Score is "+ score + " out of "+ quiz.getNumQuestions() +" . ");
	}
	private static void viewQuiz(Scanner sc)
	{
		System.out.println("Enter the Name Of Quiz : ");
		String quizSub = sc.nextLine();
		Quiz quiz= quizzes.get(quizSub);
		if(quiz == null)
		{
			System.out.println("Quiz not found");
			return;
		}
		System.out.println(" Quiz : "+quiz.getName());
		for(int i = 0;i < quiz.getNumQuestions(); i++)
		{
			Question question=quiz.getQuestion(i);
			System.out.println("Question : "+(i+1)+ " : "+ question.getQuestion());
			List<String> choices = question.getChoices();
			for(int j=0;j<choices.size();j++)
			{
				System.out.println((j+1)+ " : "+ choices.get(j));
			}
			System.out.println("Answer : "+ (question.getCorrectChoice()+1));
		}
	}
	private static void listQuiz()
	{
		System.out.println("Quizzes:");
		for(String quizSub : quizzes.keySet())
		{
			System.out.println("- "+ quizSub);
		}
	}
}

class Quiz {
	private String name;
	private List<Question> questions = new ArrayList<>();
	
	public Quiz(String name)
	{
		this.name=name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void addQuestion(Question question)
	{
		questions.add(question);
	}
	
	public Question getQuestion(int index)
	{
		return questions.get(index);
	}
	
	public int getNumQuestions()
	{
		return questions.size();
	}
}

class Question
{
	private String question;
	private List<String> choices;
	private int correctChoice;
	
	public Question(String question, List<String> choices, int correctChoice)
	{
		this.question=question;
		this.choices=choices;
		this.correctChoice=correctChoice;
	}
		public String getQuestion()
		{
			return question;
		}
		
		public List<String> getChoices()
		{
			return choices;
		}
		
		public int getCorrectChoice()
		{
			return correctChoice;
		}
	
}




