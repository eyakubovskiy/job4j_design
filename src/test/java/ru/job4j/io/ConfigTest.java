package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
        assertThat(config.value("hibernate.connection.passwords"), is(Matchers.nullValue()));
    }

    @Test
    public void whenBrokenLinesWithSpecSymbols() {
        String path = "./data/app_broken_comments.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
        assertThat(config.value("hibernate.connection.passwords"), is(Matchers.nullValue()));
    }

    @Test
    public void whenBrokenLinesWithTwoEquals() {
        String path = "./data/app_broken_two_equals.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.domain"), is("corp"));
    }

    @Test
    public void whenBrokenLinesWithTwoValue() {
        String path = "./data/app_broken_two_value.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.password"), is("passes"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenBrokenLinesWithoutEquals() {
        String path = "./data/app_broken_without_equals.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenBrokenLinesWithoutKey() {
        String path = "./data/app_broken_without_key.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenBrokenLinesWithoutValue() {
        String path = "./data/app_broken_without_value.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenBrokenLinesWithoutKeyAndValue() {
        String path = "./data/app_broken_without_key_and_value.properties";
        Config config = new Config(path);
        config.load();
    }
}
