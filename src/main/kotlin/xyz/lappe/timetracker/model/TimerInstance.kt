package xyz.lappe.timetracker.model

import java.time.Instant
import java.time.temporal.ChronoUnit
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
public class TimerInstance(@Id @GeneratedValue var id: Long? = null, var name: String, var startTime: Instant, var endTime: Instant? = null) {
    constructor() : this(name = "", startTime = Instant.MIN) {
    }


    fun duration() : Long = startTime.until(endTime, ChronoUnit.SECONDS)
}
