package io.leopard.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import io.leopard.data.factory.LeopardDefaultListableBeanFactory;

public class LeopardTestClassPathXmlApplicationContext extends ClassPathXmlApplicationContext {

	public LeopardTestClassPathXmlApplicationContext(String... configLocations) throws BeansException {
		super(configLocations, true, null);
	}

	@Override
	protected DefaultListableBeanFactory createBeanFactory() {
		DefaultListableBeanFactory beanFactory = new LeopardDefaultListableBeanFactory(getInternalParentBeanFactory());
		// logger.info("createBeanFactory:" + beanFactory.getClass().getName());
		return beanFactory;
	}
}
