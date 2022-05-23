<script setup>
import { ref, computed } from 'vue'

defineEmits(['updateEventCategory'])
const props = defineProps ({
  eventCategory: {
    type : Object ,
    default : {}
  } ,
  eventCategories : {
   type: Array,
   default: []
  }
})

const newEventCategory = computed(() => {
  return {
    id: props.eventCategory.id,
    eventCategoryName: props.eventCategory.eventCategoryName ,
    eventCategoryDescription: props.eventCategory.eventCategoryDescription ,
    eventDuration: props.eventCategory.eventDuration 
  }
})

const isDisabled = ref(false)

const disable = () => {
 if(newEventCategory.value.eventDuration > 480 || newEventCategory.value.eventDuration < 1 ){
 isDisabled.value = true
 }else
 isDisabled.value = false
}


const repeatablename = ref(false)
const uniqueName = () => {
  const categoryByName = props.eventCategories.find(e => e.eventCategoryName.trim().toLowerCase() === newEventCategory.value.eventCategoryName.trim().toLowerCase())
  if(categoryByName !== undefined && categoryByName.id !== newEventCategory.value.id) {
    isDisabled.value = true
    repeatablename.value = true
  } else {
    isDisabled.value = false
    repeatablename.value = false
  }
}
</script>
 
<template>
<div>  
  <span v-show="repeatablename" class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded absolute mx-20 top-44 ">Category Name Is Unique <button @click="repeatablename = false">x</button></span>
  <p class="text-3xl font-bold mb-3 ml-4 mt-6	">CATEGORY NAME</p>
 <p class="ml-4 "> <span class="font-bold"> CATEGORY NAME : </span>  <input type="text"  maxlength="100" class="border-2 border-black text-black ml-1 mt-16 bg-zinc-300 disabled:opacity-50 disabled:hover:cursor-not-allowed	" v-model="newEventCategory.eventCategoryName" v-on:change="uniqueName">
      <span class="font-bold"> DURATION : </span> <input type="number"  min="1" max="480"   class="border-2 border-black text-black ml-1 mt-2 bg-zinc-300 disabled:opacity-50 disabled:hover:cursor-not-allowed" v-model="newEventCategory.eventDuration" :onkeyup="disable">
    </p>
      <p><span class="font-bold ml-4"> DESCRIPTION : </span> <br>
      <textarea cols="50" rows="3" maxlength="500" class="border-2 border-black text-black ml-4 mt-1 bg-zinc-300 disabled:opacity-50 disabled:hover:cursor-not-allowed	" v-model="newEventCategory.eventCategoryDescription" ></textarea> </p>
      <button :disabled="isDisabled"  @click="$emit('updateEventCategory', {id: newEventCategory.id, eventCategoryName: newEventCategory.eventCategoryName.trimStart().trimEnd() , eventCategoryDescription: newEventCategory.eventCategoryDescription, eventDuration: newEventCategory.eventDuration})" class="text-white bg-black mr-4 border border-solid hover:bg-[#855B52]  active:bg-cyan-600 font-bold uppercase text-sm py-3 rounded outline-none focus:outline-none ease-linear transition-all duration-150 active show px-3
      disabled:opacity-50 disabled:hover:cursor-not-allowed ml-4 mb-4">
      Save</button>
</div>
</template>
 
<style>

</style>