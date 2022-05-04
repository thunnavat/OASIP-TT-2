<script setup>
import { computed } from 'vue'
defineEmits(['addEvent','cancel'])
const props = defineProps ({
  events: {
    type : Object ,
    default : {}
  }
})

const newEvent = computed(() => {
  return {
   bookingName : props.events.bookingName ,
   eventCategoryName : props.events.eventCategoryName ,
   eventStartTime : props.events.eventStartTime ,
   eventDuration : props.events.eventDuration ,
   bookingEmail : props.events.bookingEmail ,
   eventNotes : props.events.eventNotes 
  }
})
</script>
 
<template>
<div>
  <h1 class="text-3xl font-bold mb-3 ml-4	">Event</h1>
  <p class="ml-4 "> <span class="font-bold"> Name : </span>  <input type="text" class="border-2 border-black text-black ml-1 mt-2 bg-zinc-300	" v-model="newEvent.bookingName" > 
    <span class="font-bold"> Email : </span> <input type="email" class="border-2 border-black text-black ml-1 mt-2 bg-zinc-300	" v-model="newEvent.bookingEmail"  >
    <span class="font-bold"> Category : </span> <select  class="border-2 border-black text-black ml-1 mt-2 bg-zinc-300	" v-model="newEvent.eventCategoryName" >
  <option disabled >Please select one</option>
  <option>Client-side Clinic</option>
  <option>Database Clinic</option>
  <option>DevOps/Infra Clinic</option>
  <option>Project Management Clinic</option>
  <option>Server-side Clinic</option>
    </select>
    <span class="font-bold"> StartTime : </span> <input  type="datetime-local" class="text-black border-2 border-black bg-zinc-300 "  min="2022-04-19T00:01" v-model="newEvent.eventStartTime">
   <span class="font-bold"> Duration : </span>
  </p>
  <p class="ml-4"> <span class="font-bold"> Notes : </span> <br> 
   <textarea rows="4" cols="180" class="border-2 border-black bg-zinc-300" v-model="newEvent.eventNotes"></textarea> <br>
   <button @click="$emit('addEvent', {bookingName : newEvent.bookingName , eventCategoryName: newEvent.eventCategoryName , eventStartTime: newEvent.eventStartTime ,
     bookingEmail: newEvent.bookingEmail, eventNotes: newEvent.eventNotes, eventDuration: newEvent.eventDuration} )" class="text-white bg-black mr-4 border border-solid hover:bg-[#855B52]  active:bg-cyan-600 font-bold uppercase text-sm py-3 rounded outline-none focus:outline-none ease-linear transition-all duration-150 active show px-3">
     Add</button>
   <button @click="$emit('cancel')" class="text-white bg-black mr-4 border border-solid hover:bg-[#855B52]  active:bg-cyan-600 font-bold uppercase text-sm py-3 rounded outline-none focus:outline-none ease-linear transition-all duration-150 active show px-3">Cancel</button>
   </p> 
</div>
</template>
 
<style>

</style>