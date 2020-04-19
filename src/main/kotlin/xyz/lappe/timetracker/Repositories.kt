package xyz.lappe.timetracker

import org.springframework.data.repository.CrudRepository
import xyz.lappe.timetracker.model.TimerInstance
import java.util.*


interface TimerInstanceRepository : CrudRepository<TimerInstance, Long> {
    override fun findById(id: Long): Optional<TimerInstance>
}
