package com.lab_laboratorios.lab_laboratorios.infraestructure.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.ForeignKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_tests")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @ManyToOne(optional=false, fetch=FetchType.LAZY)
  @JoinColumn(name="ORDER_ID", nullable=false,
              foreignKey=@ForeignKey(name="FK_ORDERTESTS_ORDERS"))
  private Order order;


  
  @ManyToOne(optional=false, fetch=FetchType.LAZY)
  @JoinColumn(name="TEST_TYPE_ID", nullable=false,
              foreignKey=@ForeignKey(name="FK_ORDERTESTS_TESTTYPES"))
  private TestType testType;
  @ManyToOne(optional=false, fetch=FetchType.LAZY)
  @JoinColumn(name="LABORATORY_ID", nullable=false,
              foreignKey=@ForeignKey(name="FK_ORDERTESTS_LABS"))
  private Laboratory laboratory;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TestStatus status;

    private LocalDateTime scheduledStart;
    private LocalDateTime scheduledEnd;
}