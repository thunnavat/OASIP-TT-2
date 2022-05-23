<script setup>
import {ref , onBeforeMount} from 'vue'
import EditEventCategory from '../components/EditEventCategory.vue';
import EventCategoryList from '../components/EventCategoryList.vue';

const url = import.meta.env.PROD ?  import.meta.env.VITE_API_URL : 'http://localhost:8080/api';
const eventCategories = ref([])
const editMode = ref(false)

const getEventCategories = async () => {
  const res = await fetch(`${url}/eventCategories`)
  if (res.status === 200) {
    eventCategories.value = await res.json()
    console.log('Get event Category')
  } else console.log('Error, cannot get event Category')
}
onBeforeMount(async () => {
  await getEventCategories()
  sortingEventCategory(eventCategories)
})

const newestEventCategory = ref({}) 

const toEditMode = (currentEvent) => {
  newestEventCategory.value = currentEvent
  editMode.value = true
}

const updateEventCategory = async (updateEventCategory) => {
  console.log(updateEventCategory) 
  const res = await fetch(`${url}/eventCategories/${updateEventCategory.id}`, {
    method: 'PUT',
    headers: {
      'content-type': 'application/json'
    },
    body: JSON.stringify({
      eventCategoryName: updateEventCategory.eventCategoryName, eventCategoryDescription: updateEventCategory.eventCategoryDescription, eventDuration: updateEventCategory.eventDuration
    })
  })
  if (res.status === 200) {
    const editedEventCategory = await res.json()
    eventCategories.value = eventCategories.value.map((eventCategory) => eventCategory.id === editedEventCategory.id ? {...eventCategory, eventCategoryName: editedEventCategory.eventCategoryName, eventCategoryDescription: editedEventCategory.eventCategoryDescription, eventDuration: editedEventCategory.eventDuration} : eventCategory) 
    alert('Updated successfully')  
 } else console.log('Cannot update')
  cancelform()
}

const sortingEventCategory = (eventCategories) => eventCategories.value.sort((a, b) => { 
  return b.id - a.id
  })

const cancelform = () => {
  newestEventCategory.value = {}
  editMode.value = false
}


</script>
 
<template>
<div>
  <div>
    <edit-event-category
v-show="editMode"
:eventCategory="newestEventCategory"
:eventCategories = "eventCategories"
@updateEventCategory = updateEventCategory
/>
  </div>
<div>
  <event-category-list :eventCategories="eventCategories" @editEventCategory="toEditMode"/>
  </div>
  </div>
</template>
 
<style>

</style>