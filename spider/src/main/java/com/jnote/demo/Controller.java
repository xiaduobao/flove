package com.jnote.demo;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class Controller {
	public static void main(String[] args) throws Exception {
		String crawlStorageFolder = "E:/crawler";
		int numberOfCrawlers = 1;

		CrawlConfig config = new CrawlConfig();
		config.setCrawlStorageFolder(crawlStorageFolder);


		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();

		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
		// 实例化爬虫控制器
		CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);


		controller.addSeed("http://www.ics.uci.edu/~lopes/");
		//controller.addSeed("http://www.ics.uci.edu/~welling/");
		//controller.addSeed("http://www.ics.uci.edu/");


		controller.start(MyCrawler.class, numberOfCrawlers);
		//MyCrawler myCrawler = new MyCrawler();
		//myCrawler.run();
	}
}
