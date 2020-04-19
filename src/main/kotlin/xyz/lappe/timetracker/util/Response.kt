package xyz.lappe.timetracker.util

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.HttpStatusCodeException
import org.springframework.web.server.ResponseStatusException


class Response {
    companion object {
        fun ok() = ResponseEntity.ok().build<Void>()
        fun <T> ok(body: T) = ResponseEntity.ok(body)

        fun badRequest() = ResponseEntity.badRequest().build<Void>()

        fun notFound() = ResponseEntity.notFound().build<Void>()

        fun internalServerError() = ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR)

        fun notImplemented() = ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED)
    }
}
