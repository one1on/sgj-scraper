package de.one1on.sgjscraper.core;

public interface ScrapingTaskLifecycle {
    void pre();
    void scrape();
    void process();
    void post();
}
