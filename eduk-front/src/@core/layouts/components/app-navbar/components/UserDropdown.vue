<template>
  <b-nav-item-dropdown
    right
    toggle-class="d-flex align-items-center dropdown-user-link"
    class="dropdown-user"
  >
    <template #button-content>
      <div class="d-sm-flex d-none user-nav">
        <p class="user-name font-weight-bolder mb-0">
          {{ userData.email }}
        </p>
        <span class="user-status">{{
          userData.fullName || userData.username
        }}</span>
      </div>
      <b-avatar
        size="40"
        :src="userData.avatar"
        variant="light-primary"
        badge
        class="badge-minimal"
        badge-variant="success"
      >
        <feather-icon v-if="!userData.fullName" icon="UserIcon" size="22" />
      </b-avatar>
    </template>

    <b-dropdown-item
      :to="{ name: 'pages-profile' }"
      link-class="d-flex align-items-center"
    >
      <feather-icon size="16" icon="UserIcon" class="mr-50" />
      <span>프로필</span>
    </b-dropdown-item>
    <b-dropdown-item
      :to="{ name: 'apps-email' }"
      link-class="d-flex align-items-center"
    >
      <feather-icon size="16" icon="BookOpenIcon" class="mr-50" />
      <span>수업</span>
    </b-dropdown-item>
    <b-dropdown-item
      :to="{ name: 'apps-todo' }"
      link-class="d-flex align-items-center"
    >
      <feather-icon size="16" icon="MessageSquareIcon" class="mr-50" />
      <span>채팅</span>
    </b-dropdown-item>

    <b-dropdown-divider />

    <b-dropdown-item
      :to="{ name: 'pages-account-setting' }"
      link-class="d-flex align-items-center"
    >
      <feather-icon size="16" icon="SettingsIcon" class="mr-50" />
      <span>설정</span>
    </b-dropdown-item>
    <b-dropdown-item
      :to="{ name: 'main-edu-list' }"
      link-class="d-flex align-items-center"
    >
      <feather-icon size="16" icon="ArrowRightIcon" class="mr-50" />
      <span>강의 목록</span>
    </b-dropdown-item>
    <b-dropdown-item link-class="d-flex align-items-center" @click="logout">
      <feather-icon size="16" icon="LogOutIcon" class="mr-50" />
      <span>로그아웃</span>
    </b-dropdown-item></b-nav-item-dropdown
  >
</template>

<script>
import {
  BNavItemDropdown,
  BDropdownItem,
  BDropdownDivider,
  BAvatar,
} from "bootstrap-vue";
import { initialAbility } from "@/libs/acl/config";
import useJwt from "@/auth/jwt/useJwt";
import { avatarText } from "@core/utils/filter";
import Vue from "vue";

export default {
  components: {
    BNavItemDropdown,
    BDropdownItem,
    BDropdownDivider,
    BAvatar,
  },
  data() {
    return {
      userData: JSON.parse(localStorage.getItem("userData")),
      avatarText,
    };
  },
  methods: {
    logout() {
      // Remove userData from localStorage
      // ? You just removed token from localStorage. If you like, you can also make API call to backend to blacklist used token
      localStorage.removeItem(useJwt.jwtConfig.storageTokenKeyName);
      localStorage.removeItem(useJwt.jwtConfig.storageRefreshTokenKeyName);

      // Remove userData from localStorage
      localStorage.removeItem("userData");
      localStorage.removeItem("classRoomData");
      Vue.prototype.$local = {};
      // Reset ability
      this.$ability.update(initialAbility);

      // Redirect to login page
      this.$router.push({ name: "auth-login" });
    },
  },
};
</script>
