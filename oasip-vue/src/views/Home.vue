<script setup>
import {ref , onBeforeMount} from 'vue'
import EventList from '../components/EventList.vue'
import ShowDetail from '../components/ShowDetail.vue'
import dayjs from 'dayjs'
import AddEditEvent from '../components/AddEditEvent.vue'

const eventViews = ['ALL', 'DAY', 'CATEGORY', 'UPCOMING', 'PAST']
const events = ref([])
const isModal = ref(false)
const clickForBooking = ref(false)

const getEvents = async () => {
  const res = await fetch('http://localhost:8080/api/events')
  if (res.status === 200) {
    events.value = await res.json()
    console.log('Get data')
  } else console.log('Error, cannot get data')
}
onBeforeMount(async () => {
  await getEvents()
  events.value.sort((a, b) => {
    return dayjs(b.eventStartTime) - dayjs(a.eventStartTime)
  })
})

const newestEvent = ref({})
const createNewEvent = async (newEvent) => {
  const res = await fetch('http://localhost:8080/api/events', {
    method: 'POST',
    headers: {
      'content-type': 'application/json'
    },
    body: JSON.stringify({ bookingName : newEvent.bookingName , eventCategoryName: newEvent.eventCategoryName , eventStartTime: newEvent.eventStartTime ,
     bookingEmail: newEvent.bookingEmail, eventNotes: newEvent.eventNotes, eventDuration: newEvent.eventDuration })
  })
  if (res.status === 201) {
    const addedEvent = await res.json()
    events.value.push(addedEvent)
    console.log('Added sucessfully')
  } else console.log('error, cannot be added')
}

const currentEvent = ref({})
const getDetail = (event) => {
    currentEvent.value = event
    isModal.value = true
}

const closeModal = (e) => {
  isModal.value = e
}

const cancelform = () => {
  newestEvent.value = {}
  clickForBooking.value = false
}
</script>

<template>
<div>
  <div class="mt-4 flex justify-end">
    <button @click="clickForBooking = !clickForBooking" class="text-white bg-black mr-4 border border-solid hover:bg-[#855B52]  active:bg-cyan-600 font-bold uppercase text-sm py-3 rounded outline-none focus:outline-none ease-linear transition-all duration-150 active show px-3">
      BOOKING </button>
    <!-- Select Bar -->
    <select id="select-bar" class="select ml-4 mb-6 mt-3  text-black bg-blue-300 rounded font-bold">
      <option option value="ALL" v-for="(eventView, index) in eventViews" :key="index" class="font-bold">{{ eventView }}</option> 
    </select>
  </div>
    <add-edit-event v-show="clickForBooking"
    @addmovie = createNewEvent
    :events="newestEvent"
    @cancel = cancelform />
    <div>
  </div>
    <!-- Show Detail -->
    <div v-if="isModal">
      <show-detail :event="currentEvent" @close="closeModal" />
    </div>
  <!-- Show Event List -->
  <div class="absolute inset-x-4 top-2/4">
    <h2 class="text-xl font-bold ">EVENT LISTS</h2>
    <div class="box-border p-4 border-t-8 border-black">
      <div class="font-semibold flex justify-center items-center text-black box-content bg-[#c4c4c4] h-96" v-if="events.length === 0">
        <span>EMPTY</span>
      </div>
      <div v-else>
        <event-list :events="events" @detail="getDetail"/>
      </div>
    </div>
  </div>
</div>
</template>
 
<style>

</style>