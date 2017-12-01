<template>
	<div>
		<center>
			<mt-button type="default" @click="goButton()">{{name}}</mt-button><br />
			<router-link tag="mt-button" icon="more" to="ButtonTest">HTML页面跳转</router-link>
		</center>
	</div>
</template>

<script>
export default {
	data() {
		return {
			name: '通过JS页面跳转'
		};
	},
	methods: {
		goButton: function() {
			this.$router.push({path: '/Tab/Index/ButtonTest'});
		}
	}
};
</script>

<style>

</style>