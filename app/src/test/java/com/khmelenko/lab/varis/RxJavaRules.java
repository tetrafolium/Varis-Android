package com.khmelenko.lab.varis;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Rules for RxJava tests
 *
 * @author Dmytro Khmelenko (d.khmelenko@gmail.com)
 */
public final class RxJavaRules implements TestRule {

@Override
public Statement apply(final Statement base, final Description description) {
	return new SchedulerStatement(base);
}

private static final class SchedulerStatement extends Statement {

private final Statement mStatement;

SchedulerStatement(final Statement statement) {
	mStatement = statement;
}

@Override
public void evaluate() throws Throwable {
	try {
		setSchedulers();
		mStatement.evaluate();
	} finally {
		resetSchedulers();
	}
}

private void resetSchedulers() {
	RxAndroidPlugins.reset();
	RxJavaPlugins.reset();
}

private void setSchedulers() {
	RxAndroidPlugins.setInitMainThreadSchedulerHandler(
		scheduler->Schedulers.trampoline());
	RxJavaPlugins.setIoSchedulerHandler(scheduler->Schedulers.trampoline());
}
}
}
