<template>
	<div>
		<header class="mint-header">
			<div class="mint-header-button">
			</div>
			<h1 class="mint-header-title">{{title}}</h1>
			<div class="mint-header-button is-right">
				<button class="mint-button mint-button--default mint-button--normal">
						<span class="mint-button-icon"><i class="mintui mintui-more"></i></span>
						<label class="mint-button-text"></label>
					</button>
			</div>
		</header>
		<ul v-infinite-scroll="loadMore" infinite-scroll-disabled="loading" infinite-scroll-distance="10">
			<li v-for="item in list">{{ item }}</li>
		</ul>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				title: '新品',
				list: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]
			};
		},
		methods: {
			loadMore() {
				this.loading = true;
				setTimeout(() => {
					let last = this.list[this.list.length - 1];
					for (let i = 1; i <= 10; i++) {
						this.list.push(last + i);
					}
					this.loading = false;
				}, 2500);
			}
		}
	};
</script>

<style>

</style>