<script setup>
import {ref , onBeforeMount} from 'vue'
import EventList from '../components/EventList.vue'
import ShowDetail from '../components/ShowDetail.vue'
import dayjs from 'dayjs'
import AddEditEvent from '../components/AddEditEvent.vue'

const url = import.meta.env.PROD ?  import.meta.env.VITE_API_URL : 'http://localhost:8080/api';
const eventViews = ['ALL', 'DAY', 'CATEGORY', 'UPCOMING', 'PAST']
const events = ref([])
const event = ref([])
const eventCategories = ref([])
const isModal = ref(false)
const clickForBooking = ref(false)
console.log(url);

const getEvents = async () => {
  const res = await fetch(`${url}/events`)
  if (res.status === 200) {
    events.value = await res.json()
    console.log('Get data')
  } else console.log('Error, cannot get data')
}
const getEventCategories = async () => {
  const res = await fetch(`${url}/eventCategories`)
  if (res.status === 200) {
    eventCategories.value = await res.json()
    console.log('Get event Category')
  } else console.log('Error, cannot get event Category')
}
onBeforeMount(async () => {
  await getEvents()
  await getEventCategories()
  sortingEvent(events)
  event.value = events.value
})

const removeEvent = async (deleteEventId) => {
  if (confirm(`Do you want to delete event-id: ${deleteEventId} `) === true) {
    const res = await fetch(`${url}/events/${deleteEventId}` , {
      method:'DELETE'
    })
    if(res.status === 200 ){
      events.value = events.value.filter((event) => event.id !== deleteEventId)
    } else console.log('Error , cannot delete event')
  } else {
    console.log('cancel')
  }
}

const newestEvent = ref({})
const createNewEvent = async (newEvent) => {
  const res = await fetch(`${url}/events`, {
    method: 'POST',
    headers: {
      'content-type': 'application/json'
    },
    body: JSON.stringify({ bookingName : newEvent.bookingName , eventCategoryId: newEvent.eventCategoryId , eventStartTime: newEvent.eventStartTime ,
    bookingEmail: newEvent.bookingEmail, eventNotes: newEvent.eventNotes })
  })
  if (res.status === 201) {
    const addedEvent = await res.json()
    events.value.push(addedEvent)
    sortingEvent(events)
    alert('Added successfully')
  } else console.log('error, cannot be added')
  cancelform()
}
const toEditMode = (currentEvent) => {
  newestEvent.value = currentEvent
  newestEvent.value.eventStartTime = dayjs(currentEvent.eventStartTime).format('YYYY-MM-DDTHH:mm')
  newestEvent.value.eventCategoryId = currentEvent.eventCategory.id
  clickForBooking.value = true
}

const updateEvent = async (updateEvent) => {
  const res = await fetch(`${url}/events/${updateEvent.id}`, {
    method: 'PUT',
    headers: {
      'content-type': 'application/json'
    },
    body: JSON.stringify({
      eventStartTime: updateEvent.eventStartTime, eventNotes: updateEvent.eventNotes
    })
  })
  if (res.status === 200) {
    const editedEvent = await res.json()
    events.value = events.value.map((event) => event.id === editedEvent.id ? {...event, eventStartTime: editedEvent.eventStartTime, eventNotes: editedEvent.eventNotes} : event) 
  } else console.log('Cannot update')
  cancelform()
}

const sortingEvent = (events) => events.value.sort((a, b) => { 
  return dayjs(b.eventStartTime) - dayjs(a.eventStartTime)
  })

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

const selectedType = ref('ALL')
const selectedDate = ref(dayjs().format('YYYY-MM-DD'))
const selectedCategory = ref('Project Management Clinic')
const emptyMsg = ref('EMPTY')
const ascEvent = (events) => events.value.sort((a, b) => { 
    return dayjs(a.eventStartTime) - dayjs(b.eventStartTime)
 })
const change = () => {
events.value = event.value
  if(selectedType.value === 'ALL'){
    sortingEvent(events)
    emptyMsg.value = 'EMPTY'
  }
  else if(selectedType.value === 'CATEGORY'){
    events.value = events.value.filter((event) => event.eventCategory.eventCategoryName === selectedCategory.value)
    sortingEvent(events)
    emptyMsg.value = 'No Scheduled Events'
  }
  else if(selectedType.value === 'UPCOMING'){
    events.value = events.value.filter((event) => dayjs(event.eventStartTime) >= dayjs())
    ascEvent(events)
    emptyMsg.value = 'No On-Going or Upcoming Events'
  }
  else if(selectedType.value === 'PAST'){
    events.value = events.value.filter((event) => dayjs(event.eventStartTime) < dayjs())
    sortingEvent(events)
    emptyMsg.value = 'No Past Events'
  }
  else if(selectedType.value === 'DAY'){
    events.value = events.value.filter((event) => dayjs(event.eventStartTime).format('YYYY-MM-DD') === selectedDate.value)
    ascEvent(events)
    emptyMsg.value = 'No Scheduled Events'
  }
}

const unique = events.value.filter((item, i, ar) => ar.indexOf(item) === i);
console.log(unique);
</script>

<template>
<div>
  <div class="mt-4 flex justify-end">
    <button @click="clickForBooking = !clickForBooking" class="text-white bg-black mr-4 border border-solid hover:bg-[#855B52]  active:bg-cyan-600 font-bold uppercase text-sm py-3 rounded outline-none focus:outline-none ease-linear transition-all duration-150 active show px-3">
      BOOKING </button>
    <!-- Select Bar -->
    <select id="select-bar" class="select ml-4 mb-6 mt-3 mr-4  text-black bg-blue-300 rounded font-bold" v-model="selectedType" :onchange="change">
      <option v-for="(eventView, index) in eventViews" :key="index" class="font-bold">{{ eventView }}</option> 
    </select>
    <!-- User Select Specific day or category -->
    <div class="absolute top-10 right-5">
      <input type="date" v-show="selectedType === 'DAY'" v-model="selectedDate" :onchange="change">
      
      <select v-show="selectedType === 'CATEGORY'" id="select-bar" v-model="selectedCategory"  :onchange="change">
       <option v-for="(eventCategory, index) in eventCategories" :key="index" class="font-bold">{{ eventCategory.eventCategoryName }}</option> 
     </select>
    </div>
  </div>

  <!-- Show Add Event -->
  <div>
    <add-edit-event 
    v-show="clickForBooking"
    @addEvent = createNewEvent
    :event="newestEvent"
    :eventCategories="eventCategories"
    @cancel = cancelform 
    @updateEvent = updateEvent />
  </div>
  <!-- Show Detail -->
  <div v-if="isModal">
    <show-detail :event="currentEvent" @close="closeModal" />
  </div>
  <!-- Show Event List -->
  <div>
    <h2 class="text-xl font-bold ml-4 mt-4">EVENT LISTS</h2>
    <div class="box-border p-4 border-t-8 border-black">
      <div class="font-semibold flex justify-center items-center text-black box-content bg-[#c4c4c4] h-96" v-if="events.length === 0">
        <span>{{ emptyMsg }}</span>
      </div>
      <div v-else>
        <event-list :events="events" @detail="getDetail" @deleteEvent="removeEvent" @editEvent="toEditMode"/>
      </div>
    </div>
  </div>
</div>
</template>
 
<style>

</style>