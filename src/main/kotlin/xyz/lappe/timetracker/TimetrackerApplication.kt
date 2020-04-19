package xyz.lappe.timetracker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer




@SpringBootApplication
class TimetrackerApplication

fun main(args: Array<String>) {

	runApplication<TimetrackerApplication>(*args)
}


//@Configuration
//@EnableWebMvc
//class AppConfig : WebMvcConfigurer {
//	override fun addCorsMappings(registry: CorsRegistry) {
//		registry.addMapping("/*")
//				.allowedOrigins("*")
//				.allowedHeaders("Access-Control-Allow-Origin", "*")
//				.allowedHeaders("Access-Control-Allow-Headers", "Content-Type,x-requested-with")
//				.allowedHeaders("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS")
//				.maxAge(20000)
//				.allowCredentials(false)
//				.allowedMethods("*")
//	}
//}
