<script setup>
import moment from 'moment'
defineEmits(['detail'])
const props = defineProps({
  events : {
   type: Array,
   default: []
  }
})

</script>
 
<template>
<div>
  <div class="grid grid-cols-4 gap-4 bg-[#c4c4c4]">
    <div v-for="event in events" :key="event.id"  @click="$emit('detail' , event)" class="bg-white m-4 px-4 py-2 border-2 rounded border-stone-900 text-sm hover:cursor-pointer hover:ring">
      <ul>
        <li>START TIME: {{moment(event.eventStartTime).format('DD MMMM YYYY HH.mm')}}           </li>  
        <li>DURATION:   {{event.eventDuration}} mins                                            </li>
        <li>CATEGORY:   {{event.eventCategory.eventCategoryName}}                               </li>
        <li>NAME:       {{event.bookingName}}                                                   </li>
      </ul>
    </div>
  </div>
</div>
</template>
 
<style>

</style>