package ru.geekbrains.march.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class MarchMarketApplication {
	// Домашнее задание:
//	1. С помощью Spring AOP посчитайте по каждому сервису суммарное время, затрачиваемоена
//	выполнение методов этих сервисов. И по endpoint'у /statistic выдайте полученную статистику
//	клиенту.

	public static void main(String[] args) {
		SpringApplication.run(MarchMarketApplication.class, args);
	}
}
