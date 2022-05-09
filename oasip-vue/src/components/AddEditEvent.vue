<script setup>
import { ref, computed } from 'vue'
import dayjs from 'dayjs'
import utc from 'dayjs/plugin/utc'
dayjs.extend(utc)

defineEmits(['addEvent','cancel'])
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
   bookingName : props.event.bookingName ,
   eventStartTime : props.event.eventStartTime === undefined ? dayjs().utc().format('YYYY-MM-DDTHH:mm') : props.event.eventStartTime,
   eventDuration : props.event.eventDuration ,
   bookingEmail : props.event.bookingEmail ,
   eventNotes : props.event.eventNotes,
   eventCategoryId : props.event.eventCategoryId ,
  }
})

const selectedEventCategory = ref(0)

const findDuration = () => {
  const selectedCategoryId = selectedEventCategory.value
  const eventCategory = props.eventCategories.find(eventCategory => eventCategory.id === selectedCategoryId)
  newEvent.value.eventCategoryId = selectedEventCategory.value
  newEvent.value.eventDuration = eventCategory.eventDuration;
  console.log(eventCategory);
  console.log(selectedCategoryId);
  console.log(newEvent.value.eventDuration);
} 

</script>

<template>
<div>
  <h1 class="text-3xl font-bold mb-3 ml-4	">Event</h1>
  <p class="ml-4 "> <span class="font-bold"> Name : </span>  <input type="text" class="border-2 border-black text-black ml-1 mt-2 bg-zinc-300	" v-model="newEvent.bookingName" > 
    <span class="font-bold"> Email : </span> <input type="email" class="border-2 border-black text-black ml-1 mt-2 bg-zinc-300	" v-model="newEvent.bookingEmail"  >
    <span>
      <span class="font-bold"> Category : </span> 
        <select class="border-2 border-black text-black ml-1 mt-2 bg-zinc-300" v-model="selectedEventCategory" :onchange="findDuration">
          <option v-for="eventCategory in eventCategories" :key="eventCategory.id" :value="eventCategory.id">{{ eventCategory.eventCategoryName }}</option>
        </select>
    </span>
    <span class="font-bold"> StartTime : </span> <input  type="datetime-local" class="text-black border-2 border-black bg-zinc-300 "  min="2022-04-19T00:01" v-model="newEvent.eventStartTime">
   <span class="font-bold"> Duration : </span> <input disabled type="text" :value="newEvent.eventDuration" class="text-black border-2 border-black bg-zinc-300 opacity-50 hover:cursor-not-allowed" >
  </p>
  <p class="ml-4"> <span class="font-bold"> Notes : </span> <br> 
   <textarea rows="4" cols="180" class="border-2 border-black bg-zinc-300" v-model="newEvent.eventNotes"></textarea> <br>
   <button @click="$emit('addEvent', {bookingName : newEvent.bookingName , eventCategoryId: newEvent.eventCategoryId , eventStartTime: dayjs(newEvent.eventStartTime).utc().format(),
     bookingEmail: newEvent.bookingEmail, eventNotes: newEvent.eventNotes, eventDuration: newEvent.eventDuration} )" class="text-white bg-black mr-4 border border-solid hover:bg-[#855B52]  active:bg-cyan-600 font-bold uppercase text-sm py-3 rounded outline-none focus:outline-none ease-linear transition-all duration-150 active show px-3">
     Add</button>
   <button @click="$emit('cancel')" class="text-white bg-black mr-4 border border-solid hover:bg-[#855B52]  active:bg-cyan-600 font-bold uppercase text-sm py-3 rounded outline-none focus:outline-none ease-linear transition-all duration-150 active show px-3">Cancel</button>
   </p> 
</div>
</template>
 
<style>

</style>