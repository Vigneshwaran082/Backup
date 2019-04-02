package com.app.sugarcrush.junit.custom.runner;

import java.util.List;

import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

/*
 * 
 * Custom Test suite which will be repeated for 5 times 
 * 
 * */
public class RepeatTestSuite extends Suite{

	protected RepeatTestSuite(Class<?> klass, Class<?>[] suiteClasses) throws InitializationError {
		super(klass, suiteClasses);
		
	}
	 public RepeatTestSuite(Class<?> klass, RunnerBuilder builder) throws InitializationError { 
		 super(klass,builder);
	 }
	
	 public RepeatTestSuite(RunnerBuilder builder, Class<?>[] classes) throws InitializationError { 
		 super(builder,classes);
	 }
	 
	 protected RepeatTestSuite(RunnerBuilder builder, Class<?> klass, Class<?>[] suiteClasses) throws InitializationError {
		 super( builder,  klass, suiteClasses);
	 }
	 
	 protected RepeatTestSuite(Class<?> klass, List<Runner> runners) throws InitializationError { 
		 super(klass, runners);
	 }
	 
	 @Override
	public void run(RunNotifier notifier) {
		for(int i =0 ; i < 5; i++) {
				super.run(notifier); 
		}
	}
	 
}
