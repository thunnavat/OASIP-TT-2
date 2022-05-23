<script setup>
import { ref, computed } from 'vue'
import dayjs from 'dayjs'
import utc from 'dayjs/plugin/utc'
dayjs.extend(utc)

defineEmits(['addEvent','cancel','updateEvent'])
const props = defineProps ({
  event: {
    type : Object ,
    default : {}
  },
  eventCategories: {
    type: Array,
    require: true
  }
})
const newEvent = computed(() => {
  return {
    id: props.event.id,
    bookingName: props.event.bookingName ,
    eventStartTime: props.event.eventStartTime ,
    eventDuration: props.event.eventDuration ,
    bookingEmail: props.event.bookingEmail ,
    eventNotes: props.event.eventNotes,
    eventCategoryId: props.event.eventCategoryId
  }
})

const selectedEventCategory = ref(0)
const findDuration = () => {
  noCategory.value = false
  const eventCategory = props.eventCategories.find(eventCategory => eventCategory.id === selectedEventCategory.value)
  newEvent.value.eventCategoryId = selectedEventCategory.value
  newEvent.value.eventDuration = eventCategory.eventDuration;
} 

const startTime = ref(dayjs().format('YYYY-MM-DDTHH:mm'))
const past = ref(false)
const noCategory = ref(false)
const noStartTime = ref(false)
const showNotes = ref(false)
const isDisabled = ref(false)
const editShow = ref(false)
const noValidEmail = ref(false)
const checkEmailNull = ref(false)
const noName = ref(false)
const isOverlap = ref(false)
const checkDate = ref(false)

const changeStartTime = () => {
  noStartTime.value = false
  isOverlap.value = false
  showNotes.value = false
  editShow.value = false
  showNotes.value = false
  checkDate.value = true
  if(newEvent.value.id === undefined) {
    if(startTime.value <= dayjs().format('YYYY-MM-DDTHH:mm')) {
      past.value = true
      isDisabled.value = true
    } else {
      newEvent.value.eventStartTime = startTime.value
      past.value = false
      isDisabled.value = false
    } 
  } else {
    if(newEvent.value.eventStartTime <= dayjs().format('YYYY-MM-DDTHH:mm')){
      past.value = true
      isDisabled.value = true
    }
    else {
      past.value = false
      isDisabled.value = false
    }
  }
}

const url = import.meta.env.PROD ?  import.meta.env.VITE_API_URL : 'http://localhost:8080/api';
const eventsByDateAndCategory = ref([])

const getEventsByDateAndCategory = async (eventCategoryId, startDateTime) => {
  const res = await fetch(`${url}/events/${eventCategoryId}/${startDateTime}`)
  if(res.status === 200) {
    eventsByDateAndCategory.value = await res.json()
  } else console.log('Cannot get events by date and category')
}

const checkOverlapTime = async (eventCategoryId, startDateTime) => {
  if(newEvent.value.eventCategoryId === undefined && newEvent.value.eventStartTime === undefined) {
    noCategory.value = true
    noStartTime.value = true
  } else if(newEvent.value.eventStartTime === undefined) {
    noStartTime.value = true
  } else if(newEvent.value.eventCategoryId === undefined) {
    noCategory.value = true;
  } else {
    await getEventsByDateAndCategory(eventCategoryId, startDateTime)
    isOverlap.value = eventsByDateAndCategory.value.some(e => ((e.eventStartTime < dayjs(newEvent.value.eventStartTime).utc().format() && (dayjs(e.eventStartTime).utc().add(e.eventDuration, 'm').format()) > dayjs(newEvent.value.eventStartTime).utc().format()) ||
      (e.eventStartTime >= dayjs(newEvent.value.eventStartTime).utc().format() && e.eventStartTime < (dayjs(newEvent.value.eventStartTime).add(newEvent.value.eventDuration, 'm').utc().format()))))
    if(isOverlap.value === false && past.value === false){
      showNotes.value = true
      editShow.value = !editShow.value
      checkDate.value = !checkDate.value
    }
  }
}

const validateEmail = () => {
  checkEmailNull.value = false
    if(String(newEvent.value.bookingEmail)
    .toLowerCase()
    .match(
     /^[^\s@]+@[^\s@]+\.[^\s@]+$/
    ) === null && newEvent.value.bookingEmail !== undefined){
      noValidEmail.value = true
    }
  else{
    noValidEmail.value = false
  }
  
};
const check = () => {
  validateEmail()
  if(newEvent.value.bookingName === undefined && newEvent.value.bookingEmail === undefined){
    isDisabled.value = true
    checkEmailNull.value = true
    noName.value = true
  }
  else if(newEvent.value.bookingName === undefined || newEvent.value.bookingName === ''){
    isDisabled.value = true
    noName.value = true
  }
  else if(newEvent.value.bookingEmail === undefined || newEvent.value.bookingEmail === ''){
    isDisabled.value = true
    checkEmailNull.value = true
  }
  else if(noValidEmail.value === true){
    isDisabled.value = true
  }
  else{
    isDisabled.value = false
    noName.value = false
    checkEmailNull.value = false
  }
}

console.log(dayjs().format());
console.log(dayjs().utc().startOf('day').format())

</script>

<template>
<div>
  <div>
    <h1 class="text-xl font-bold mb-3 ml-4">ADD NEW EVENT</h1>
    <span>
      
    <span v-show="noCategory" class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded absolute mx-24 top-40 ">Please Select Category <button @click="noCategory = false">x</button></span>
      <span class="font-bold ml-4"> Category : </span> 
      <!-- ใช้ if else เเสดง eventCategories ตอนกด edit ได้เเต่โค๊ตมันจะซ้ำๆนะ ถ้าไม่โอเคเปลี่ยนกลับเป็นโค็ตเดิมได้เลย ^_^ -->
      <!-- <select :disabled="newEvent.id > 0" class="border-2  border-black text-black ml-1 mt-2 bg-zinc-300 disabled:hover:cursor-not-allowed" v-model="selectedEventCategory" :onchange="findDuration">
        <option v-for="eventCategory in eventCategories" :key="eventCategory.id" :value="eventCategory.id">{{ eventCategory.eventCategoryName }}</option>
      </select> -->
      <select v-if="newEvent.id === undefined" class="border-2  border-black text-black ml-1 mt-2 bg-zinc-300" v-model="selectedEventCategory" :onchange="findDuration">
        <option v-for="eventCategory in eventCategories" :key="eventCategory.id" :value="eventCategory.id">{{ eventCategory.eventCategoryName }}</option>
      </select>
      <select v-else disabled class="border-2  border-black text-black ml-1 mt-2 bg-zinc-300 disabled:opacity-50 disabled:hover:cursor-not-allowed" v-model="newEvent.eventCategoryId" :onchange="findDuration">
        <option v-for="eventCategory in eventCategories" :key="eventCategory.id" :value="eventCategory.id">{{ eventCategory.eventCategoryName }}</option>
      </select>
    </span>
    <span v-show="past" class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded absolute mx-20 top-40 ">Please enter future date and time <button @click="past = false , isDisabled = false">x</button></span>
    <span v-show="noStartTime" class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded absolute mx-20 top-40 ">Please Enter Start Time <button @click=" noStartTime = false">x</button></span>
    <span v-show="isOverlap" class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded absolute mx-20 top-40 ">Start time is overlap <button @click="isOverlap = false">x</button></span>
    <span class="font-bold"> StartTime : </span> <input v-if="newEvent.id === undefined" type="datetime-local"  class="text-black border-2 border-black bg-zinc-300 "  min="2022-04-19T00:01" v-model="startTime" :onchange="changeStartTime ">
                                                  <input v-else type="datetime-local" class="text-black border-2 border-black bg-zinc-300 "  min="2022-04-19T00:01" v-model="newEvent.eventStartTime" :onchange="changeStartTime ">
    <span class="font-bold"> Duration : </span> <input disabled type="text" :value="newEvent.eventDuration" class="text-black border-2 border-black bg-zinc-300 opacity-50 hover:cursor-not-allowed">
    <button v-show="(!editShow && newEvent.id === undefined) || checkDate" @click="checkOverlapTime(newEvent.eventCategoryId, dayjs(newEvent.eventStartTime).utc().startOf('day').format())" class="text-white bg-black mr-4 border border-solid hover:bg-[#855B52]  active:bg-cyan-600 font-bold uppercase text-sm py-3 rounded outline-none focus:outline-none ease-linear transition-all duration-150 active show px-3 ml-4">Next</button>
  </div>
  <div v-show="(showNotes || newEvent.id > 0) && !checkDate">

    <p class="ml-4 "> <span class="font-bold"> Name : </span>  <input type="text" :disabled="newEvent.id > 0" maxlength="100" class="border-2 border-black text-black ml-1 mt-2 bg-zinc-300 disabled:opacity-50 disabled:hover:cursor-not-allowed	" v-model="newEvent.bookingName" @click="noName = false" > 
    <span v-show="noName" class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative mx-2 ">Please Insert Your Name <button @click=" noName = false">x</button></span>
      <span class="font-bold"> Email : </span> <input type="email" :disabled="newEvent.id > 0" maxlength="100"  class="border-2 border-black text-black ml-1 mt-2 bg-zinc-300 disabled:opacity-50 disabled:hover:cursor-not-allowed" v-model="newEvent.bookingEmail"  :onchange="check">
          <span v-show="noValidEmail" class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded absolute mx-20 right">Please Enter A Valid Email Address <button @click=" noValidEmail = false">x</button></span>
          <span v-show="checkEmailNull" class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded absolute mx-20 right">Please Insert Email Address <button @click=" checkEmailNull = false">x</button></span>
    </p>
    <p class="ml-4"> <span class="font-bold"> Notes : </span> <br> 
    <textarea rows="4" maxlength="500" cols="180" class="border-2 border-black bg-zinc-300" v-model="newEvent.eventNotes"></textarea> <br>
    <div>
      <button :disabled="isDisabled" v-if="newEvent.id > 0" @click="changeStartTime() , isDisabled === true ? '' : $emit('updateEvent', {id: newEvent.id, eventStartTime: dayjs(newEvent.eventStartTime).utc().format(), eventNotes: newEvent.eventNotes}) " class="text-white bg-black mr-4 border border-solid hover:bg-[#855B52]  active:bg-cyan-600 font-bold uppercase text-sm py-3 rounded outline-none focus:outline-none ease-linear transition-all duration-150 active show px-3
      disabled:opacity-50 disabled:hover:cursor-not-allowed">
      Save</button>
      <button :disabled="isDisabled && (noName || checkEmailNull)" v-else  @click= "check() , isDisabled === true ? '' : $emit('addEvent', {bookingName : newEvent.bookingName , eventCategoryId: newEvent.eventCategoryId , eventStartTime: dayjs(newEvent.eventStartTime).utc().format(),
      bookingEmail: newEvent.bookingEmail, eventNotes: newEvent.eventNotes} )"  class="text-white bg-black mr-4 border border-solid hover:bg-[#855B52]  active:bg-cyan-600 font-bold uppercase text-sm py-3 rounded outline-none focus:outline-none ease-linear transition-all duration-150 active show px-3 disabled:opacity-50 disabled:hover:cursor-not-allowed">
      Add</button>
      <button @click="$emit('cancel')" class="text-white bg-black mr-4 border border-solid hover:bg-[#855B52]  active:bg-cyan-600 font-bold uppercase text-sm py-3 rounded outline-none focus:outline-none ease-linear transition-all duration-150 active show px-3">Cancel</button>
    </div>
    </p>
  </div> 
</div>
</template>
 
<style>

</style>