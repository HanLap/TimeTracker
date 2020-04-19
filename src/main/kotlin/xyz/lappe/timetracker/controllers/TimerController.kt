package xyz.lappe.timetracker.controllers

import jdk.nashorn.internal.objects.NativeRegExp.test
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import xyz.lappe.timetracker.TimerInstanceRepository
import xyz.lappe.timetracker.model.InstantUpdate
import xyz.lappe.timetracker.model.TimerInstance
import xyz.lappe.timetracker.util.Response

@CrossOrigin(origins = ["*"], methods = [RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.POST, RequestMethod.GET])
@RestController
class TimerController(private val timerRepo: TimerInstanceRepository) {

  @GetMapping("/timer")
  fun getTimers(): MutableIterable<TimerInstance> {

    return timerRepo.findAll()
  }

  @GetMapping("/timer/{id}")
  fun getTimerById(@PathVariable("id") id: String): ResponseEntity<*> {

    val timer = timerRepo.findById(
        try {
          id.toLong()
        } catch (e: NumberFormatException) {
          return Response.badRequest()
        })

    if (timer.isEmpty) {
      return Response.notFound()
    }

    return Response.ok(timer)
  }

  @PostMapping("/timer")
  fun postTimer(@RequestBody timer: TimerInstance?): ResponseEntity<*> {

    if (timer === null) {
      return Response.badRequest()
    }

    return Response.ok(timerRepo.save(timer))
  }

  @DeleteMapping("/timer/{id}")
  fun deleteTimer(@PathVariable("id") id: String): ResponseEntity<Void> {
    val timer = timerRepo.findById(try {
      id.toLong()
    } catch (e: NumberFormatException) {
      return Response.badRequest()
    })

    if (timer.isEmpty) {
      return Response.notFound()
    }

    timerRepo.delete(timer.get())

    return Response.ok()
  }

  @PutMapping("/timer/{id}")
  fun changeName(@PathVariable("id") id: String, @RequestBody body: Map<String, String>?): ResponseEntity<Void> {

    if (body == null || !body.containsKey("name") && body["name"] == null) {
      return Response.badRequest()
    }

    val timer = timerRepo.findById(try {
      id.toLong()
    } catch (e: NumberFormatException) {
      return Response.badRequest()
    })

    if (timer.isEmpty) {
      return Response.notFound()
    }

    timer.get()
        .apply { name = body.getValue("name") }
        .also { timerRepo.save(it) }


    return Response.ok()
  }

  @PutMapping("/timer/{id}/stop")
  fun stopTimer(@PathVariable("id") id: String, @RequestBody body: InstantUpdate?): ResponseEntity<Void> {

    if (body?.instant == null) {
      return Response.badRequest()
    }

    val optional = timerRepo.findById(try {
      id.toLong()
    } catch (e: NumberFormatException) {
      return Response.badRequest()
    })

    if (optional.isEmpty) {
      return Response.notFound()
    }

    val timer = optional.get()

    if (timer.endTime != null) {
      return ResponseEntity(HttpStatus.CONFLICT)
    }

    timer.apply { endTime = body.instant }
        .also { timerRepo.save(it) }

    return Response.ok()
  }

  @PutMapping("/timer/{id}/start")
  fun startTimer(@PathVariable("id") id: String, @RequestBody body: InstantUpdate?): ResponseEntity<Void> {

    if (body?.instant == null) {
      return Response.badRequest()
    }

    val optional = timerRepo.findById(try {
      id.toLong()
    } catch (e: NumberFormatException) {
      return Response.badRequest()
    })

    if (optional.isEmpty) {
      return Response.notFound()
    }

    val timer = optional.get()

    if (timer.endTime == null) {
      return ResponseEntity(HttpStatus.CONFLICT)
    }

    timer.apply {
      startTime = body.instant.minusSeconds(duration())
      endTime = null
    }.also { timerRepo.save(it) }

    return Response.ok()
  }
}


