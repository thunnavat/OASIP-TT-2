<script setup>
import dayjs from 'dayjs'
import utc from 'dayjs/plugin/utc'
dayjs.extend(utc)

defineEmits(['detail','deleteEvent','editEvent'])
defineProps({
  events : {
   type: Array,
   default: []
  }
})

</script>
 
<template>
<div>
  <div class="grid grid-cols-1 gap-1 2xl:grid-cols-4 xl:grid-cols-3 lg:grid-cols-2 bg-[#c4c4c4] p-4"> 
    <div v-for="event in events" :key="event.id"  class="bg-white m-4 px-4 pt-2 pb-4 leading-8 border-4 rounded border-stone-900 text-sm">
      <ul>
        <li class="flex justify-end font-bold ">
          <span class="text-[#878787] mr-1">CATEGORY: </span>{{ event.eventCategory.eventCategoryName }}
        </li>
        <li class="font-bold">
          <span class="text-[#878787]">NAME: </span>{{ event.bookingName }}
        </li>
        <li class="font-bold">
          <span class="text-[#878787]">START TIME: </span>{{ dayjs(event.eventStartTime).format('DD MMMM YYYY HH.mm') }}
        </li>  
        <li class="font-bold">
          <span class="text-[#878787] mr-1">DURATION: </span><span class="mr-28">{{ event.eventDuration }} mins</span>  
        </li>
        <li class="relative mt-6">
          <button @click="$emit('deleteEvent', event.id)" class="text-white bg-black mr-4 border border-solid hover:bg-[#855B52]  active:bg-cyan-600 font-bold uppercase text-sm py-3 rounded outline-none focus:outline-none ease-linear transition-all duration-150 active show px-3">
            DELETE</button>
            <button @click="$emit('editEvent', event)" class="text-white bg-black mr-4 border border-solid hover:bg-[#855B52]  active:bg-cyan-600 font-bold uppercase text-sm py-3 rounded outline-none focus:outline-none ease-linear transition-all duration-150 active show px-3">
            EDIT</button>
          <span @click="$emit('detail' , event)" class="grid justify-end leading-3 justify-items-center absolute bottom-0 right-0 hover:cursor-pointer">
            <span class="iconify" data-icon="noto:bear" style="width: 38px; height: 38px;"></span>
            <span class="text-[#878787] font-bold underline hover:text-black" style="font-size: 11px;">MORE DETAILS</span>
          </span>
        </li>
      </ul>
    </div>
  </div>
</div>
</template>
 
<style>

</style>