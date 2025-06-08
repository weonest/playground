package com.schedule

class Scheduler(
    private val meetingRepository: MeetingRepository,
) {
    fun schedule(recurrenceMeeting: RecurringMeeting) {
        val meetings = meetingRepository.findByDayOfWeek(recurrenceMeeting.getDayOfWeek())

        validateOverlap(recurrenceMeeting, meetings)

        meetingRepository.insert(recurrenceMeeting)
    }

    fun canSchedule(recurrenceMeeting: RecurringMeeting): Boolean {
        val meetings = meetingRepository.findByDayOfWeek(recurrenceMeeting.getDayOfWeek())

        try {
            validateOverlap(recurrenceMeeting, meetings)
        } catch (e: IllegalArgumentException) {
            return false
        }

        return true
    }

    fun reschedule(recurrenceMeeting: RecurringMeeting) {
        val meetings = meetingRepository.findByDayOfWeek(recurrenceMeeting.getDayOfWeek())

        // Remove the old meeting if it exists
        meetingRepository.insert(recurrenceMeeting)

        validateOverlap(recurrenceMeeting, meetings)
    }

    private fun validateOverlap(
        recurrenceMeeting: RecurringMeeting,
        meetings: List<RecurringMeeting>
    ) {
        for (meeting in meetings) {
            meeting.validateOverlap(recurrenceMeeting)
        }
    }
}
