package com.app.sugarcrush.repo.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/*
 * Use the RepeatTestSuite.class Runner when you want to repeat the test many times, Check RepeatTestSuite to increase the number
 * NOTE: Remove UserRepoTest.class from @SuiteClasses annotation in case of any error , this may give 
 * error because it uses different application context configuration.
 * */
/*@RunWith(RepeatTestSuite.class)*/
@RunWith(Suite.class)
@SuiteClasses({ ItemRepoTest.class, CakeRepoTest.class , UserRepoTest.class })/*Remove UserRepoTest in case of error */
public class SugarCrushRepositoryTestSuite {

}
