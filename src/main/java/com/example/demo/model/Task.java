package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TASK")
public class Task {
        @Id
        @SequenceGenerator(name = "id_seq", sequenceName = "SEQ_CASE_USER_ID", allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
        @Column(name = "ID", updatable = false)
        private Long id;

        @Column(name = "TITLE", nullable = false, unique = true)
        private String title;

        @Column(name = "DESCRIPTION", nullable = false)
        private String description;

        @Column(name = "STATUS", nullable = false)
        private String statue;

        public Task(final Long id, final String title, final String description, final String statue) {
                this.id = id;
                this.title = title;
                this.description = description;
                this.statue = statue;
        }

        public Task() {

        }

        public Long getId() {
                return id;
        }

        public String getTitle() {
                return title;
        }

        public String getDescription() {
                return description;
        }

        public String getStatue() {
                return statue;
        }
}
